package com.practice.BankService.service;

import com.practice.BankService.dto.BankAccount;
import com.practice.BankService.dto.UtilityAccount;
import com.practice.BankService.entity.BankAccountEntity;
import com.practice.BankService.entity.UtilityAccountEntity;
import com.practice.BankService.mapper.BankAccountMapper;
import com.practice.BankService.mapper.UtilityAccountMapper;
import com.practice.BankService.repository.BankAccountRepository;
import com.practice.BankService.repository.UtilityAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService
{
    private BankAccountMapper bankAccountMapper= new BankAccountMapper();
    private UtilityAccountMapper utilityAccountMapper= new UtilityAccountMapper();

    private final BankAccountRepository bankAccountRepository;
    private final UtilityAccountRepository utilityAccountRepository;
    public BankAccount readBankAccount(String accountNumber)
    {
        BankAccountEntity entity= bankAccountRepository.findByNumber(accountNumber).get();
        return bankAccountMapper.convertToDto(entity);
    }
    public UtilityAccount readUtilityAccount(String provider)
    {
        UtilityAccountEntity utilityAccountEntity= utilityAccountRepository.findByProvideName(provider).get();
        return utilityAccountMapper.convertToDto(utilityAccountEntity);
    }
    public UtilityAccount readUtilityAccount(Long id)
    {
        return utilityAccountMapper.convertToDto(utilityAccountRepository.findById(id).get());

    }


}
