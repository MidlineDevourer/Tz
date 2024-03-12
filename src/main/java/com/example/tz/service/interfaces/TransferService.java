package com.example.tz.service.interfaces;

import com.example.tz.models.entity.AccountNumber;

import java.math.BigDecimal;

public interface TransferService {

    void deposit(AccountNumber accountNumber, BigDecimal bigDecimal);

    void withdraw(AccountNumber accountNumber, BigDecimal bigDecimal);

    void transfer(AccountNumber fromAccountNumber, AccountNumber recipientAccountNumber, BigDecimal bigDecimal);

}
