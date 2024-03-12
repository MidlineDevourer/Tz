package com.example.tz.controller.rest;

import com.example.tz.models.dto.TransactionHistoryDto;
import com.example.tz.models.mapper.TransactionHistoryMapper;
import com.example.tz.service.interfaces.TransactionHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Транзакции", description = "Контроллер для работы с транзакциями")
@RequestMapping("/api/transaction")
public class TransactionHistoryController {

    private final TransactionHistoryService transactionHistoryService;
    private final TransactionHistoryMapper transactionHistoryMapper;

    @Operation(description = "Получение транзакции по id")
    @GetMapping("/getByID/{transactionId}")
    public ResponseEntity<TransactionHistoryDto> getTransactionById(@PathVariable Long transactionId) {
        return new ResponseEntity<>(
                transactionHistoryMapper.toDto(
                        transactionHistoryService.findById(transactionId)), HttpStatus.OK);
    }

    @Operation(description = "Получение всех транзакции аккаунта")
    @GetMapping("/getByAccountId/{accountId}")
    private ResponseEntity<List<TransactionHistoryDto>> getAllTransactions(@PathVariable Long accountId) {
        return new ResponseEntity<>(
                transactionHistoryMapper.toDtoList(
                        transactionHistoryService.findAllByAccount(accountId)), HttpStatus.OK);
    }


}
