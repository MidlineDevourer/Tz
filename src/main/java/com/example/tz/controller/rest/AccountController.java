package com.example.tz.controller.rest;

import com.example.tz.models.dto.AccountDto;
import com.example.tz.models.mapper.AccountMapper;
import com.example.tz.service.interfaces.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Аккаунты", description = "Контроллер для работы с аккаунтами")
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Operation(summary = "Получение аккаунта по id")
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        return new ResponseEntity<>(accountMapper.toDto(accountService.findById(id)), HttpStatus.OK);
    }

    @Operation(summary = "Получение всех аккаунтов")
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccount() {
        return new ResponseEntity<>(accountMapper.toDtoList(accountService.findByAll()), HttpStatus.OK);
    }

    @Operation(summary = "Создание аккаунта")
    @PostMapping
    public ResponseEntity<HttpStatus> createAccount(@RequestBody AccountDto accountDto) {
        accountService.saveAccount(accountMapper.toEntity(accountDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
