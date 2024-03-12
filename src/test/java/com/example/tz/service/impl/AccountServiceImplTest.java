package com.example.tz.service.impl;

import com.example.tz.exception.impl.EntityNotFoundException;
import com.example.tz.models.entity.Account;
import com.example.tz.models.entity.AccountNumber;
import com.example.tz.repository.AccountRepository;
import com.example.tz.service.interfaces.AccountNumberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountNumberService accountNumberService;

    @Mock
    private ValidationServiceImpl validationService;

    @Test
    public void testFindById_EntityNotFoundException() {
        Long id = 1L;
        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> accountService.findById(id));
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Account account = new Account();
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));

        assertSame(account, accountService.findById(id));
    }

    @Test
    void testFindByAll() {
        List<Account> accountList = new ArrayList<>();
        when(accountRepository.findAll()).thenReturn(accountList);

        assertSame(accountList, accountService.findByAll());
    }

    @Test
    void testSaveAccount_NewAccount() {
        Account account = new Account();
        account.setNameAccount("TestName");
        account.setPinCode("1234");
        when(accountRepository.findByNameAccountAndPinCode(account.getNameAccount(), account.getPinCode())).thenReturn(null);
        when(accountNumberService.create(any(Account.class))).thenReturn(new AccountNumber());

        accountService.saveAccount(account);

        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void testSaveAccount_OldAccount() {
        Account account = new Account();
        account.setNameAccount("TestName");
        account.setPinCode("1234");
        account.setAccountNumbers(new ArrayList<>());
        when(accountRepository.findByNameAccountAndPinCode(account.getNameAccount(), account.getPinCode())).thenReturn(account);
        when(accountNumberService.create(any(Account.class))).thenReturn(new AccountNumber());

        accountService.saveAccount(account);

        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void testCreate() {
        Account account = new Account();
        when(accountNumberService.create(any(Account.class))).thenReturn(new AccountNumber());

        assertNotNull(accountService.create(account));
    }

    @Test
    void testCheckAccount_WithNewEntity() {
        Account account = new Account();
        when(accountRepository.findByNameAccountAndPinCode(account.getNameAccount(), account.getPinCode())).thenReturn(null);

        assertTrue(accountService.checkAccount(account));
    }

    @Test
    void testCheckAccount_WithOldEntity() {
        Account account = new Account();
        when(accountRepository.findByNameAccountAndPinCode(account.getNameAccount(), account.getPinCode())).thenReturn(account);

        assertFalse(accountService.checkAccount(account));
    }

}
