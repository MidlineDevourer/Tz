package com.example.tz.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Schema(description = "Информация о счётах")
public class AccountNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Schema(description = "id счёта")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "account_id")
    @Schema(description = "Аккаунт которому принадлежит счет")
    private Account account;

    @Schema(description = "Номер счёта")
    private String accountNumber;

    @Schema(description = "Баланс")
    private BigDecimal balance;

}
