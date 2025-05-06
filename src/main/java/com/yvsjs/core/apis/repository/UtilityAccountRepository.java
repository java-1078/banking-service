package com.yvsjs.core.apis.repository;

import com.yvsjs.core.apis.entity.UtilityAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilityAccountRepository extends JpaRepository<UtilityAccountEntity, Long> {
    Optional<UtilityAccountEntity> findByProviderName(String provider);
}
