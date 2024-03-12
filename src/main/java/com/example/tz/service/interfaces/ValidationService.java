package com.example.tz.service.interfaces;

import java.math.BigDecimal;

public interface ValidationService {

    void checkNameForEmpty(String nameAccount);

    void checkPin(String correctPin, String checkPin);

    void checkingCorrectPin(String pin);

    void checkBalance(BigDecimal accountBalance, BigDecimal checkBalance);

    void checkDepositIsGreaterThanZero(BigDecimal deposit);

}
