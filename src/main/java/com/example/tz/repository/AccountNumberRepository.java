package com.example.tz.repository;

import com.example.tz.models.entity.AccountNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountNumberRepository extends JpaRepository<AccountNumber, Long> {

    Optional<AccountNumber> findByAccountNumber(String accountNumber);

}