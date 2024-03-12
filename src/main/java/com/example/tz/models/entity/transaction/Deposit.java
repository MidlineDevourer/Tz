package com.example.tz.models.entity.transaction;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Deposit {

    private String accountNumber;

    private BigDecimal balance;

    private String pinCode;

}
