package com.yvsjs.core.apis.service;

import com.yvsjs.core.apis.dto.BankAccount;
import com.yvsjs.core.apis.requests.FundTransferRequest;
import com.yvsjs.core.apis.requests.UtilityPaymentRequest;
import com.yvsjs.core.apis.response.FundTransferResponse;
import com.yvsjs.core.apis.response.UtilityPaymentResponse;

import java.math.BigDecimal;

public interface TransactionService {

    FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest);

    void validateBalance(BankAccount bankAccount, BigDecimal amount);

    String internalFundTransfer(BankAccount fromBankAccount, BankAccount toBankAccount, BigDecimal amount);

    UtilityPaymentResponse utilPayment(UtilityPaymentRequest utilityPaymentRequest);
}
