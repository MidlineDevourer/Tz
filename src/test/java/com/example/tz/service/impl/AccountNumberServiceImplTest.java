package com.example.tz.service.impl;

import com.example.tz.exception.impl.EntityNotFoundException;
import com.example.tz.models.entity.Account;
import com.example.tz.models.entity.AccountNumber;
import com.example.tz.models.entity.transaction.Deposit;
import com.example.tz.models.entity.transaction.Transfer;
import com.example.tz.models.entity.transaction.Withdraw;
import com.example.tz.repository.AccountNumberRepository;
import com.example.tz.service.interfaces.TransferService;
import com.example.tz.service.interfaces.ValidationService;
import com.example.tz.utils.GeneratorAccountNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountNumberServiceImplTest {

    @InjectMocks
    private AccountNumberServiceImpl accountNumberService;

    @Mock
    private AccountNumberRepository accountNumberRepository;

    @Mock
    private GeneratorAccountNumber generatorAccountNumber;

    @Mock
    private ValidationService validationService;

    @Mock
    private TransferService transferService;

    @Test
    void testFindById_EntityNotFoundException() {
        Long id = 1L;
        when(accountNumberRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> accountNumberService.findById(id));
    }

    @Test
    void testFindById() {
        Long id = 1L;
        AccountNumber accountNumber = new AccountNumber();
        when(accountNumberRepository.findById(id)).thenReturn(Optional.of(accountNumber));

        assertSame(accountNumber, accountNumberService.findById(id));
    }

    @Test
    void testFindByAll() {
        List<AccountNumber> accountNumberList = new ArrayList<>();
        when(accountNumberRepository.findAll()).thenReturn(accountNumberList);

        assertSame(accountNumberList, accountNumberService.findByAll());
    }

    @Test
    void testCreate() {
        Account account = new Account();

        assertNotNull(accountNumberService.create(account));
        verify(generatorAccountNumber, times(1)).generateAccountNumber();
    }

    @Test
    void testGetAccountNumber_EntityNotFoundException() {
        AccountNumber accountNumber = new AccountNumber();
        when(accountNumberRepository.findByAccountNumber(accountNumber.getAccountNumber())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> accountNumberService.getAccountNumber(accountNumber.getAccountNumber()));
    }

    @Test
    void testGetAccountNumber() {
        AccountNumber accountNumber = new AccountNumber();
        when(accountNumberRepository.findByAccountNumber(accountNumber.getAccountNumber())).thenReturn(Optional.of(accountNumber));

        assertSame(accountNumber, accountNumberService.getAccountNumber(accountNumber.getAccountNumber()));
    }

    @Test
    void testUpdate() {
        AccountNumber accountNumber = new AccountNumber();

        accountNumberService.update(accountNumber);

        verify(accountNumberRepository, times(1)).save(accountNumber);
    }

    @Test
    void testDeposit() {
        Deposit deposit = new Deposit();
        deposit.setPinCode("1234");
        AccountNumber accountNumber = new AccountNumber();
        Account account = new Account();
        account.setPinCode("1234");
        accountNumber.setAccount(account);
        when(accountNumberRepository.findByAccountNumber(accountNumber.getAccountNumber()))
                .thenReturn(Optional.of(accountNumber));

        accountNumberService.deposit(deposit);

        verify(accountNumberRepository, times(1)).save(accountNumber);
    }

    @Test
    void testWithdraw() {
        Withdraw withdraw = new Withdraw();
        withdraw.setPinCode("1234");
        AccountNumber accountNumber = new AccountNumber();
        Account account = new Account();
        account.setPinCode("1234");
        accountNumber.setAccount(account);
        when(accountNumberRepository.findByAccountNumber(accountNumber.getAccountNumber()))
                .thenReturn(Optional.of(accountNumber));

        accountNumberService.withdraw(withdraw);

        verify(accountNumberRepository, times(1)).save(accountNumber);
    }

    @Test
    void testTransfer() {
        Transfer transfer = new Transfer();
        AccountNumber accountNumber = new AccountNumber();
        AccountNumber accountRecipient = new AccountNumber();
        Account account = new Account();
        accountNumber.setAccountNumber("1111222233334444");
        accountRecipient.setAccountNumber("4444333322221111");
        transfer.setAccountNumber("1111222233334444");
        transfer.setAccountRecipient("4444333322221111");
        transfer.setPinCode("1234");
        account.setPinCode("1234");
        accountNumber.setAccount(account);
        when(accountNumberRepository.findByAccountNumber(transfer.getAccountNumber()))
                .thenReturn(Optional.of(accountNumber));
        when(accountNumberRepository.findByAccountNumber(transfer.getAccountRecipient()))
                .thenReturn(Optional.of(accountRecipient));

        accountNumberService.transfer(transfer);

        verify(accountNumberRepository, times(1)).save(accountNumber);
        verify(accountNumberRepository, times(1)).save(accountRecipient);
    }

}
