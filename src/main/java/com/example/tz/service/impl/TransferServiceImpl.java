package com.example.tz.service.impl;

import com.example.tz.models.entity.AccountNumber;
import com.example.tz.service.interfaces.TransferService;
import com.example.tz.service.interfaces.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class TransferServiceImpl implements TransferService {

    private final ValidationService validationService;

    @Override
    public void deposit(AccountNumber accountNumber, BigDecimal bigDecimal) {
        accountNumber.setBalance(accountNumber.getBalance().add(bigDecimal));
    }

    @Override
    public void withdraw(AccountNumber accountNumber, BigDecimal bigDecimal) {
        validationService.checkBalance(accountNumber.getBalance(), bigDecimal);
        accountNumber.setBalance(accountNumber.getBalance().subtract(bigDecimal));
    }

    @Override
    public void transfer(AccountNumber fromAccountNumber, AccountNumber recipientAccountNumber, BigDecimal bigDecimal) {
        validationService.checkBalance(fromAccountNumber.getBalance(), bigDecimal);
        fromAccountNumber.setBalance(fromAccountNumber.getBalance().subtract(bigDecimal));
        recipientAccountNumber.setBalance(recipientAccountNumber.getBalance().add(bigDecimal));
    }


}
