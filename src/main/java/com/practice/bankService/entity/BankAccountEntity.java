package com.practice.BankService.entity;

import com.practice.BankService.model.AccountStatus;
import com.practice.BankService.model.AccountType;
import jakarta.persistence.*;
import lombok.Data;

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
