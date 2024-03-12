package com.example.tz.service.impl;

import com.example.tz.exception.impl.IncorrectProfileDataException;
import com.example.tz.exception.impl.NegativeBalanceException;
import com.example.tz.exception.impl.NegativeDepositException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ValidationServiceImplTest {

    @InjectMocks
    private ValidationServiceImpl validationService;

    @Test
    void testCheckNameForEmpty() {
        String nameAccount = "";

        assertThrows(IncorrectProfileDataException.class, () -> validationService.checkNameForEmpty(nameAccount));
    }

    @Test
    void testCheckPin() {
        String correctPin = "1111";
        String checkPin = "2222";

        assertThrows(IncorrectProfileDataException.class, () -> validationService.checkPin(correctPin, checkPin));
    }

    @Test
    void testCheckingCorrectPin() {
        String pin1 = "A123";
        String pin2 = "123";
        String pin3 = "12345";

        assertThrows(IncorrectProfileDataException.class, () -> validationService.checkingCorrectPin(pin1));
        assertThrows(IncorrectProfileDataException.class, () -> validationService.checkingCorrectPin(pin2));
        assertThrows(IncorrectProfileDataException.class, () -> validationService.checkingCorrectPin(pin3));

    }

    @Test
    void testCheckBalance() {
        BigDecimal accountBalance = new BigDecimal(1);
        BigDecimal checkBalance = new BigDecimal(2);

        assertThrows(NegativeBalanceException.class, () -> validationService.checkBalance(accountBalance, checkBalance));
    }

    @Test
    void testCheckDepositIsGreaterThanZero() {
        BigDecimal value = new BigDecimal(-1);

        assertThrows(NegativeDepositException.class, () -> validationService.checkDepositIsGreaterThanZero(value));
    }

}
