package com.example.tz.models.dto;

import com.example.tz.models.entity.Account;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransactionHistoryDto(Long id,
                                    Account account,
                                    Account accountRecipient,
                                    BigDecimal transferAmount,
                                    String transactionType) {
}
