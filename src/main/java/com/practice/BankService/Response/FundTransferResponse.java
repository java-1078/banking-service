package com.practice.BankService.Response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FundTransferResponse
{
    private String message;
    private String transactionId;
}
