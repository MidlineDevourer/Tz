package com.example.tz.service.impl;

import com.example.tz.models.entity.AccountNumber;
import com.example.tz.service.interfaces.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TransferServiceImplTest {

    @InjectMocks
    private TransferServiceImpl transferService;

    @Mock
    private ValidationService validationService;

    @Test
    void testDeposit() {
        AccountNumber accountNumber = new AccountNumber();
        accountNumber.setBalance(BigDecimal.ZERO);
        BigDecimal bigDecimal = new BigDecimal(10);

        transferService.deposit(accountNumber, bigDecimal);

        assertEquals(new BigDecimal(10), accountNumber.getBalance());

    }

    @Test
    void testWithdraw() {
        AccountNumber accountNumber = new AccountNumber();
        accountNumber.setBalance(new BigDecimal(10));
        BigDecimal bigDecimal = new BigDecimal(5);

        transferService.withdraw(accountNumber, bigDecimal);

        assertEquals(new BigDecimal(5), accountNumber.getBalance());
    }

    @Test
    void testTransfer() {
        AccountNumber fromAccountNumber = new AccountNumber();
        fromAccountNumber.setBalance(new BigDecimal(10));
        AccountNumber recipientAccountNumber = new AccountNumber();
        recipientAccountNumber.setBalance(BigDecimal.ZERO);
        BigDecimal bigDecimal = new BigDecimal(5);

        transferService.transfer(fromAccountNumber, recipientAccountNumber, bigDecimal);

        assertEquals(new BigDecimal(5), recipientAccountNumber.getBalance());
        assertEquals(new BigDecimal(5), fromAccountNumber.getBalance());
    }
}