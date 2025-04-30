package com.practice.bankService.entity;

import com.practice.bankService.model.AccountStatus;
import com.practice.bankService.model.AccountType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "banking_core_account")
public class BankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private BigDecimal availableBalance;
    private BigDecimal actualBalance;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
