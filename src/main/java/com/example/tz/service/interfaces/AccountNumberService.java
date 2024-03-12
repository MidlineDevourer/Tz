package com.example.tz.service.interfaces;

import com.example.tz.models.entity.Account;
import com.example.tz.models.entity.AccountNumber;
import com.example.tz.models.entity.transaction.Deposit;
import com.example.tz.models.entity.transaction.Transfer;
import com.example.tz.models.entity.transaction.Withdraw;

import java.util.List;

public interface AccountNumberService {

    AccountNumber findById(Long id);

    List<AccountNumber> findByAll();

    AccountNumber create(Account account);

    AccountNumber getAccountNumber(String accountNumber);

    void deposit(Deposit deposit);

    void withdraw(Withdraw withdraw);

    void transfer(Transfer transfer);

    void update(AccountNumber accountNumber);

}
