package com.yvsjs.core.apis.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundTransferRequest {
    private String fromAccount; // sender’s account number
    private String toAccount;   // receiver’s account number
    private BigDecimal amount;  // amount to transfer

}
