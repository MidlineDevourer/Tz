package com.example.tz.service.impl;

import com.example.tz.exception.impl.EntityNotFoundException;
import com.example.tz.models.entity.TransactionHistory;
import com.example.tz.repository.TransactionHistoryRepository;
import com.example.tz.service.interfaces.AccountService;
import com.example.tz.service.interfaces.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    private final TransactionHistoryRepository transactionHistoryRepository;
    private final AccountService accountService;

    @Override
    public TransactionHistory findById(Long id) {
        return transactionHistoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Перевод с id = " + id + " не найден."));
    }

    @Override
    public List<TransactionHistory> findAllByAccount(Long accountId) {
        return transactionHistoryRepository.findAllByAccount(accountService.findById(accountId));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(TransactionHistory transactionHistory) {
        transactionHistoryRepository.save(transactionHistory);
    }

}
