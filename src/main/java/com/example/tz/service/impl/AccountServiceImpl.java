package com.example.tz.service.impl;

import com.example.tz.exception.impl.EntityNotFoundException;
import com.example.tz.models.entity.Account;
import com.example.tz.models.entity.AccountNumber;
import com.example.tz.repository.AccountRepository;
import com.example.tz.service.interfaces.AccountNumberService;
import com.example.tz.service.interfaces.AccountService;
import com.example.tz.service.interfaces.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountNumberService accountNumberService;
    private final ValidationService validationService;

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Нет аккаунта с id = " + id));
    }

    @Override
    public List<Account> findByAll() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional
    public void saveAccount(Account account) {
        validationService.checkNameForEmpty(account.getNameAccount());
        validationService.checkingCorrectPin(account.getPinCode());
        Account createAccount;
        if (checkAccount(account)) {
            createAccount = create(account);
        } else {
            createAccount = accountRepository.findByNameAccountAndPinCode(account.getNameAccount(), account.getPinCode());
            createAccount.getAccountNumbers().add(accountNumberService.create(createAccount));
        }
        accountRepository.save(createAccount);
    }

    @Override
    public Account create(Account account) {
        Account createAccount = new Account();
        List<AccountNumber> accountNumberList = new ArrayList<>();
        accountNumberList.add(accountNumberService.create(createAccount));
        createAccount.setAccountNumbers(accountNumberList);
        createAccount.setNameAccount(account.getNameAccount());
        createAccount.setPinCode(account.getPinCode());
        return createAccount;
    }

    @Override
    public boolean checkAccount(Account account) {
        Account checkAccount = accountRepository.findByNameAccountAndPinCode(account.getNameAccount(), account.getPinCode());
        return checkAccount == null;
    }

}
