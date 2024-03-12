package com.example.tz.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Schema(description = "информация о транзакциях")
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Schema(description = "id транзакции")
    private Long id;

    @ManyToOne
    @Schema(description = "Аккаунт осуществляющий перевод")
    private Account account;

    @JsonBackReference
    @ManyToOne
    @Schema(description = "Аккаунт получающий перевод")
    private Account accountRecipient;

    @Schema(description = "Сумма перевода")
    private BigDecimal transferAmount;

    @Schema(description = "Тип транзакции")
    private String transactionType;

}
