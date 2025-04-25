package com.practice.BankService.repository;

import com.practice.BankService.entity.UtilityAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilityAccountRepository extends JpaRepository<UtilityAccountEntity,Long>
{
     Optional<UtilityAccountEntity> findByProvideName(String provider);
}
