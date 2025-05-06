package com.yvsjs.core.apis.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FundTransferResponse {
    private String message;
    private String transactionId;
}
