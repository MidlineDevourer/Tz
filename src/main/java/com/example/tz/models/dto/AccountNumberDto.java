package com.example.tz.models.dto;

import com.example.tz.models.entity.Account;

import java.math.BigDecimal;

public record AccountNumberDto(Long id,
                               Account account,
                               String accountNumber,
                               BigDecimal balance) {
}
