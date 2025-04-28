package com.practice.BankService.service;

import com.practice.BankService.Response.FundTransferResponse;
import com.practice.BankService.dto.BankAccount;
import com.practice.BankService.entity.BankAccountEntity;
import com.practice.BankService.entity.TransactionEntity;
import com.practice.BankService.model.TransactionType;
import com.practice.BankService.repository.BankAccountRepository;
import com.practice.BankService.repository.TransactionRepository;
import com.practice.BankService.requests.FundTransferRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
//This method is handling a fund transfer request between two accounts.
public class TransactionService
{
    private final AccountService accountService;
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    public FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest)
    {
        BankAccount fromBankAccount = accountService.readBankAccount(fundTransferRequest.getFromAccount());// so here from request it is getting an accountnumber and searching the record and the Purpose is : the account from which the funds will be deducted.


        BankAccount toBankAccount = accountService.readBankAccount(fundTransferRequest.getToAccount()); //

        validateBalance(fromBankAccount,fundTransferRequest.getAmount());// so here it is checking the balance of that record
        String transactionId = internalFundTransfer(fromBankAccount,toBankAccount,fundTransferRequest.getAmount());//?
        return  FundTransferResponse.builder().message("Transaction successfully completed").transactionId(transactionId).build();//?

    }
    private void validateBalance(BankAccount bankAccount, BigDecimal amount)
    {
        if(bankAccount.getActualBalance().compareTo(BigDecimal.ZERO) < 0 || bankAccount.getActualBalance().compareTo(amount)<0)
        {
            throw new RuntimeException();
        }
    }
    public String internalFundTransfer(BankAccount fromBankAccount,BankAccount toBankAccount,BigDecimal amount)
    {
        String transactionId = UUID.randomUUID().toString();
        BankAccountEntity fromBankAccountEntity = bankAccountRepository.findByNumber(fromBankAccount.getNumber().get());
        BankAccountEntity toBankAccountEntity = bankAccountRepository.findByNumber(toBankAccount.getNumber().get());

        fromBankAccountEntity.setActualBalance(fromBankAccountEntity.getActualBalance().subtract(amount));
        fromBankAccountEntity.setAvailableBalance(fromBankAccountEntity.getActualBalance().subtract(amount));
        bankAccountRepository.save(fromBankAccountEntity);


       transactionRepository.save(TransactionEntity.builder().transactionType(TransactionType.FUND_Transfer).referenceNumber(toBankAccountEntity.getNumber()).transactionId(transactionId).account(fromBankAccountEntity).account(fromBankAccountEntity).amount(amount.negate()).build());

       toBankAccountEntity.setAvailableBalance(toBankAccountEntity.getAvailableBalance().add(amount));
       toBankAccountEntity.setActualBalance(toBankAccountEntity.);
    }

    public UtilityPaymentResponse utilPayment(UtilityPaymentRequest utilityPaymentRequest)
    {
        String transactionId = UUID.randomUUID().toString();
        BankAccount fromBankAccount= accountService.readBankAccount(utilityPaymentRequest.getAccount());
        validateBalance(fromBankAccount,utilityPaymentRequest.getAmount());

        UtilityAccount utilityAccount= accountService.readUtilityAccount(utilityPaymentRequest.getProviderId());

        BankAccountEntity fromAccount = bankAccountRepository.findByNumber(fromBankAccount.getNumber()).get();


        fromAccount.setActualBalance(fromAccount.getActualBalance().subtract(utilityPaymentRequest.getAmount()));
        fromAccount.setAvailableBalance(fromAccount.getAvailableBalance().subtract(utilityPaymentRequest.getAmount()));
        transactionRepository.save(TransactionEntity.builder().transactionType(TransactionType.UTILITY_PAYMENT).account(fromAccount).transactionId(transactionId).referenceNumber(utilityPaymentRequest.getReferenceNumber()).amount(utilityPaymentRequest.getAmount().negate()).build());
        return UtilityPaymentResponse.builder().message("Utility payment Sucessfully completed").transactionId(transactionId).build();
    }






}
