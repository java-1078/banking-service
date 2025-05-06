package com.yvsjs.core.apis.requests;

import com.yvsjs.core.apis.types.AccountStatus;
import com.yvsjs.core.apis.types.AccountType;
import lombok.Data;

@Data
public class AccountCreationRequest {
    private String number;
    private AccountType type;
    private AccountStatus status;
    private String identificationNumber;
}
