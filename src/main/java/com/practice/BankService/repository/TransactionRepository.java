package com.practice.BankService.repository;

import com.practice.BankService.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>
{

}
