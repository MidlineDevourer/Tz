package com.example.tz.service.interfaces;

import com.example.tz.models.entity.TransactionHistory;

import java.util.List;

public interface TransactionHistoryService {

    TransactionHistory findById(Long id);

    List<TransactionHistory> findAllByAccount(Long accountId);

    void save(TransactionHistory transactionHistory);

}
