package com.practice.BankService.dto;

import com.practice.BankService.model.AccountStatus;
import com.practice.BankService.model.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class BankAccount {
    private Long id;
    private String number;
    private AccountType type;
    private AccountStatus status;
    private BigDecimal availableBalance;
    private BigDecimal actualBalance;
    private User user;
}
