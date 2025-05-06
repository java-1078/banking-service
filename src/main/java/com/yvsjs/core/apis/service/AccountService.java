package com.yvsjs.core.apis.service;

import com.yvsjs.core.apis.dto.BankAccount;
import com.yvsjs.core.apis.dto.UtilityAccount;
import com.yvsjs.core.apis.requests.AccountCreationRequest;
import com.yvsjs.core.apis.response.AccountCreationResponse;

public interface AccountService {
    AccountCreationResponse createAccount(AccountCreationRequest accountCreationRequest);

    BankAccount readBankAccount(String accountNumber);

    UtilityAccount readUtilityAccount(String provider);

    UtilityAccount readUtilityAccount(Long id);

}
