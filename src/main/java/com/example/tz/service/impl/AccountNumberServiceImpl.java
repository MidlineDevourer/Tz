package com.example.tz.service.impl;

import com.example.tz.exception.impl.EntityNotFoundException;
import com.example.tz.models.entity.Account;
import com.example.tz.models.entity.AccountNumber;
import com.example.tz.models.entity.transaction.Deposit;
import com.example.tz.models.entity.transaction.Transfer;
import com.example.tz.models.entity.transaction.Withdraw;
import com.example.tz.repository.AccountNumberRepository;
import com.example.tz.service.interfaces.AccountNumberService;
import com.example.tz.service.interfaces.TransferService;
import com.example.tz.service.interfaces.ValidationService;
import com.example.tz.utils.GeneratorAccountNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountNumberServiceImpl implements AccountNumberService {

    private final AccountNumberRepository accountNumberRepository;
    private final GeneratorAccountNumber generatorAccountNumber;
    private final ValidationService validationService;
    private final TransferService transferService;

    @Override
    public AccountNumber findById(Long id) {
        return accountNumberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Счёт с id = " + id + " не найден."));
    }

    @Override
    public List<AccountNumber> findByAll() {
        return accountNumberRepository.findAll();
    }

    @Override
    @Transactional
    public AccountNumber create(Account account) {
        return AccountNumber.builder()
                .balance(BigDecimal.ZERO)
                .accountNumber(generatorAccountNumber.generateAccountNumber())
                .account(account)
                .build();
    }

    @Override
    public AccountNumber getAccountNumber(String accountNumber) {
        return accountNumberRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException("Счёт с номером = " + accountNumber + " не найден."));
    }

    @Override
    @Transactional
    public void deposit(Deposit deposit) {
        validationService.checkDepositIsGreaterThanZero(deposit.getBalance());
        AccountNumber accountNumber = getAccountNumber(deposit.getAccountNumber());
        validationService.checkPin(accountNumber.getAccount().getPinCode(), deposit.getPinCode());
        transferService.deposit(accountNumber, deposit.getBalance());
        update(accountNumber);
    }

    @Override
    @Transactional
    public void withdraw(Withdraw withdraw) {
        validationService.checkDepositIsGreaterThanZero(withdraw.getBalance());
        AccountNumber accountNumber = getAccountNumber(withdraw.getAccountNumber());
        validationService.checkPin(accountNumber.getAccount().getPinCode(), withdraw.getPinCode());
        transferService.withdraw(accountNumber, withdraw.getBalance());
        update(accountNumber);
    }

    @Override
    @Transactional
    public void transfer(Transfer transfer) {
        validationService.checkDepositIsGreaterThanZero(transfer.getBalance());
        AccountNumber fromAccountNumber = getAccountNumber(transfer.getAccountNumber());
        validationService.checkPin(fromAccountNumber.getAccount().getPinCode(), transfer.getPinCode());
        AccountNumber recipientAccountNumber = getAccountNumber(transfer.getAccountRecipient());
        transferService.transfer(fromAccountNumber, recipientAccountNumber, transfer.getBalance());
        update(fromAccountNumber);
        update(recipientAccountNumber);
    }

    @Override
    @Transactional
    public void update(AccountNumber accountNumber) {
        accountNumberRepository.save(accountNumber);
    }


}
