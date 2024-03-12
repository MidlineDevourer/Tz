package com.example.tz.controller.rest;

import com.example.tz.models.entity.transaction.Deposit;
import com.example.tz.models.entity.transaction.Transfer;
import com.example.tz.models.entity.transaction.Withdraw;
import com.example.tz.service.interfaces.AccountNumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/transfers")
@Tag(name = "Операции по счёту", description = "Контроллер для работы с счётом")
public class TransferController {

    private final AccountNumberService accountNumberService;

    @Operation(description = "Пополнение")
    @PutMapping("/deposit")
    public ResponseEntity<HttpStatus> deposit(@RequestBody Deposit deposit) {
        accountNumberService.deposit(deposit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(description = "Списание")
    @PutMapping("/withdraw")
    public ResponseEntity<HttpStatus> withdraw(@RequestBody Withdraw withdraw) {
        accountNumberService.withdraw(withdraw);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(description = "Перевод")
    @PutMapping("/transfer")
    public ResponseEntity<HttpStatus> transfer(@RequestBody Transfer transfer) {
        accountNumberService.transfer(transfer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
