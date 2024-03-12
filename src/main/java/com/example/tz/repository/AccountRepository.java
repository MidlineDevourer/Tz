package com.example.tz.repository;

import com.example.tz.models.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByNameAccountAndPinCode(String nameAccount, String pinCode);

}
