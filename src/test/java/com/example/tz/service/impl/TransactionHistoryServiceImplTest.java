package com.example.tz.service.impl;

import com.example.tz.exception.impl.EntityNotFoundException;
import com.example.tz.models.entity.Account;
import com.example.tz.models.entity.TransactionHistory;
import com.example.tz.repository.TransactionHistoryRepository;
import com.example.tz.service.interfaces.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionHistoryServiceImplTest {

    @InjectMocks
    private TransactionHistoryServiceImpl transactionHistoryService;

    @Mock
    private TransactionHistoryRepository transactionHistoryRepository;

    @Mock
    private AccountService accountService;

    @Test
    void testFindById_EntityNotFoundException() {
        Long transactionId = 1L;
        when(transactionHistoryRepository.findById(transactionId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> transactionHistoryService.findById(transactionId));
    }

    @Test
    void testFindById() {
        Long transactionId = 1L;
        TransactionHistory transactionHistory = new TransactionHistory();
        when(transactionHistoryRepository.findById(transactionId)).thenReturn(Optional.of(transactionHistory));

        assertSame(transactionHistory, transactionHistoryService.findById(transactionId));
    }

    @Test
    void testFindAllByAccount() {
        Long accountId = 2L;
        List<TransactionHistory> transactionHistoryList = new ArrayList<>();
        when(accountService.findById(accountId)).thenReturn(new Account());
        when(transactionHistoryRepository.findAllByAccount(any(Account.class))).thenReturn(transactionHistoryList);

        assertSame(transactionHistoryList, transactionHistoryService.findAllByAccount(accountId));
    }

    @Test
    void testSave() {
        TransactionHistory transactionHistory = new TransactionHistory();

        transactionHistoryService.save(transactionHistory);

        verify(transactionHistoryRepository, times(1)).save(transactionHistory);
    }
}