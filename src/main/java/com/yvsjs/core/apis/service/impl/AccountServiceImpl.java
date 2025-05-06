package com.yvsjs.core.apis.service.impl;

import com.yvsjs.core.apis.dto.BankAccount;
import com.yvsjs.core.apis.dto.UtilityAccount;
import com.yvsjs.core.apis.entity.BankAccountEntity;
import com.yvsjs.core.apis.entity.UtilityAccountEntity;
import com.yvsjs.core.apis.exception.RecordNotFoundException;
import com.yvsjs.core.apis.mapper.BankAccountMapper;
import com.yvsjs.core.apis.mapper.UtilityAccountMapper;
import com.yvsjs.core.apis.repository.BankAccountRepository;
import com.yvsjs.core.apis.repository.UtilityAccountRepository;
import com.yvsjs.core.apis.requests.AccountCreationRequest;
import com.yvsjs.core.apis.response.AccountCreationResponse;
import com.yvsjs.core.apis.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final BankAccountMapper bankAccountMapper;
    private final UtilityAccountMapper utilityAccountMapper;
    private final BankAccountRepository bankAccountRepository;
    private final UtilityAccountRepository utilityAccountRepository;

    @Override
    public AccountCreationResponse createAccount(AccountCreationRequest accountCreationRequest) {
        return null;
    }

    @Override
    public BankAccount readBankAccount(String accountNumber) {
        BankAccountEntity entity = bankAccountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new RecordNotFoundException("Bank account not found with number: " + accountNumber));
        return bankAccountMapper.convertToDto(entity);
    }

    @Override
    public UtilityAccount readUtilityAccount(String provider) {
        UtilityAccountEntity utilityAccountEntity = utilityAccountRepository.findByProviderName(provider)
                .orElseThrow(() -> new RecordNotFoundException("Utility Provider not found with name: " + provider));
        return utilityAccountMapper.convertToDto(utilityAccountEntity);
    }

    public UtilityAccount readUtilityAccount(Long id) {
        UtilityAccountEntity utilityAccountEntity = utilityAccountRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("UtilityAccount not found with id: " + id));
        return utilityAccountMapper.convertToDto(utilityAccountEntity);
    }

}
