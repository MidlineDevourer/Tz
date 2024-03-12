package com.example.tz.service.interfaces;

import com.example.tz.models.entity.Account;

import java.util.List;


public interface AccountService {

    Account findById(Long id);

    List<Account> findByAll();

    void saveAccount(Account account);

    Account create(Account account);

    boolean checkAccount(Account account);

}
