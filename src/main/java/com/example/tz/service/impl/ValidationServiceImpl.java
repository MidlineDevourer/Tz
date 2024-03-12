package com.example.tz.service.impl;

import com.example.tz.exception.impl.IncorrectProfileDataException;
import com.example.tz.exception.impl.NegativeBalanceException;
import com.example.tz.exception.impl.NegativeDepositException;
import com.example.tz.service.interfaces.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class ValidationServiceImpl implements ValidationService {


    @Override
    public void checkNameForEmpty(String nameAccount) {
        if (nameAccount.isEmpty()) {
            throw new IncorrectProfileDataException("Отсутствует имя профиля");
        }
    }

    @Override
    public void checkPin(String correctPin, String checkPin) {
        if (!correctPin.equals(checkPin)) {
            throw new IncorrectProfileDataException("Неверный Pin-code");
        }
    }

    @Override
    public void checkingCorrectPin(String pin) {
        if (!Pattern.matches("[0-9]{4}", pin)) {
            throw new IncorrectProfileDataException("Некорректный Pin-code");
        }
    }

    @Override
    public void checkBalance(BigDecimal accountBalance, BigDecimal checkBalance) {
        if (accountBalance.compareTo(checkBalance) < 0) {
            throw new NegativeBalanceException("Недостаточно денег");
        }
    }

    @Override
    public void checkDepositIsGreaterThanZero(BigDecimal deposit) {
        System.out.println(deposit.compareTo(BigDecimal.ZERO));
        if (deposit.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeDepositException("Депозит меньше нуля");
        }
    }
}
