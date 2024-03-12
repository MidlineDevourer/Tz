package com.example.tz.exception.impl;

public class NegativeDepositException extends RuntimeException {

    public NegativeDepositException(String message) {
        super(message);
    }

}
