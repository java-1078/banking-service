package com.yvsjs.core.apis.repository;

import com.yvsjs.core.apis.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByIdentificationNumber(String identificationNumber);
}
