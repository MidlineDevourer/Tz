package com.example.tz.models.entity.transaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transfer extends Withdraw {

    private String accountRecipient;

}
