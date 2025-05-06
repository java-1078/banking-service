package com.yvsjs.core.apis.service.impl;

import com.yvsjs.core.apis.dto.BankAccount;
import com.yvsjs.core.apis.dto.UtilityAccount;
import com.yvsjs.core.apis.entity.BankAccountEntity;
import com.yvsjs.core.apis.entity.TransactionEntity;
import com.yvsjs.core.apis.repository.BankAccountRepository;
import com.yvsjs.core.apis.repository.TransactionRepository;
import com.yvsjs.core.apis.requests.FundTransferRequest;
import com.yvsjs.core.apis.requests.UtilityPaymentRequest;
import com.yvsjs.core.apis.response.FundTransferResponse;
import com.yvsjs.core.apis.response.UtilityPaymentResponse;
import com.yvsjs.core.apis.service.TransactionService;
import com.yvsjs.core.apis.types.TransactionType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountServiceImpl accountService;
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest) {
        BankAccount fromBankAccount = accountService.readBankAccount(fundTransferRequest.getFromAccount());
        BankAccount toBankAccount = accountService.readBankAccount(fundTransferRequest.getToAccount());
        validateBalance(fromBankAccount, fundTransferRequest.getAmount());
        String transactionId = internalFundTransfer(fromBankAccount, toBankAccount, fundTransferRequest.getAmount());
        return FundTransferResponse.builder().message("Transaction successfully completed").transactionId(transactionId).build();
    }

    @Override
    public void validateBalance(BankAccount bankAccount, BigDecimal amount) {
        if (bankAccount.getActualBalance().compareTo(BigDecimal.ZERO) < 0 || bankAccount.getActualBalance().compareTo(amount) < 0) {
            throw new RuntimeException();
        }
    }

    @Override
    public String internalFundTransfer(BankAccount fromBankAccount, BankAccount toBankAccount, BigDecimal amount) {
        String transactionId = UUID.randomUUID().toString();
        BankAccountEntity fromBankAccountEntity = bankAccountRepository.findByNumber(fromBankAccount.getNumber()).get();
        BankAccountEntity toBankAccountEntity = bankAccountRepository.findByNumber(toBankAccount.getNumber()).get();

        fromBankAccountEntity.setActualBalance(fromBankAccountEntity.getActualBalance().subtract(amount));
        fromBankAccountEntity.setAvailableBalance(fromBankAccountEntity.getActualBalance().subtract(amount));
        bankAccountRepository.save(fromBankAccountEntity);

        transactionRepository.save(TransactionEntity.builder().transactionType(TransactionType.FUND_Transfer).referenceNumber(toBankAccountEntity.getNumber()).transactionId(transactionId).account(fromBankAccountEntity).account(fromBankAccountEntity).amount(amount.negate()).build());

        toBankAccountEntity.setAvailableBalance(toBankAccountEntity.getAvailableBalance().add(amount));
        toBankAccountEntity.setActualBalance(toBankAccountEntity.getActualBalance().add(amount));
        bankAccountRepository.save(toBankAccountEntity);
        transactionRepository.save(TransactionEntity.builder().transactionType(TransactionType.FUND_Transfer)
                .referenceNumber(toBankAccountEntity.getNumber())
                .transactionId(transactionId)
                .account(toBankAccountEntity).amount(amount).build());

        return transactionId;
    }

    @Override
    public UtilityPaymentResponse utilPayment(UtilityPaymentRequest utilityPaymentRequest) {
        String transactionId = UUID.randomUUID().toString();
        BankAccount fromBankAccount = accountService.readBankAccount(utilityPaymentRequest.getAccount());
        validateBalance(fromBankAccount, utilityPaymentRequest.getAmount());

        UtilityAccount utilityAccount = accountService.readUtilityAccount(utilityPaymentRequest.getProviderId());

        BankAccountEntity fromAccount = bankAccountRepository.findByNumber(fromBankAccount.getNumber()).get();

        fromAccount.setActualBalance(fromAccount.getActualBalance().subtract(utilityPaymentRequest.getAmount()));
        fromAccount.setAvailableBalance(fromAccount.getAvailableBalance().subtract(utilityPaymentRequest.getAmount()));
        transactionRepository.save(TransactionEntity.builder().transactionType(TransactionType.UTILITY_PAYMENT).account(fromAccount).transactionId(transactionId).referenceNumber(utilityPaymentRequest.getReferenceNumber()).amount(utilityPaymentRequest.getAmount().negate()).build());
        return UtilityPaymentResponse.builder().message("Utility payment Successfully completed").transactionId(transactionId).build();
    }

}
