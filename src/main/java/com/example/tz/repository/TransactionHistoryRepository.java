package com.example.tz.repository;

import com.example.tz.models.entity.Account;
import com.example.tz.models.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    List<TransactionHistory> findAllByAccount(Account account);

}
