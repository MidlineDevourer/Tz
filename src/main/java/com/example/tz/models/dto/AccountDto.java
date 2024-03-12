package com.example.tz.models.dto;

import com.example.tz.models.entity.AccountNumber;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record AccountDto(Long id,
                         String nameAccount,
                         @Pattern(regexp = "[0-9]{4}", message = "Некорректный Pin-code")
                         String pinCode,
                         List<AccountNumber> accountNumbers) {
}
