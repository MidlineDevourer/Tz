package com.example.tz.aspect;

import com.example.tz.models.dto.TransactionHistoryDto;
import com.example.tz.models.entity.transaction.Deposit;
import com.example.tz.models.entity.transaction.Transfer;
import com.example.tz.models.entity.transaction.Withdraw;
import com.example.tz.models.mapper.TransactionHistoryMapper;
import com.example.tz.service.interfaces.AccountNumberService;
import com.example.tz.service.interfaces.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AccountNumberAspect {

    private final TransactionHistoryService transactionHistoryService;
    private final TransactionHistoryMapper transactionHistoryMapper;
    private final AccountNumberService accountNumberService;

    @AfterReturning("execution(void deposit(*)) && args(deposit)")
    public void afterDepositSuccess(Deposit deposit) {
        transactionHistoryService.save(transactionHistoryMapper.toEntity(TransactionHistoryDto.builder()
                .transferAmount(deposit.getBalance())
                .account(accountNumberService.getAccountNumber(deposit.getAccountNumber()).getAccount())
                .transactionType("deposit")
                .build()));
    }

    @AfterReturning("execution(void withdraw(*)) && args(withdraw)")
    public void afterWithdrawSuccess(Withdraw withdraw) {
        transactionHistoryService.save(transactionHistoryMapper.toEntity(TransactionHistoryDto.builder()
                .transferAmount(withdraw.getBalance())
                .account(accountNumberService.getAccountNumber(withdraw.getAccountNumber()).getAccount())
                .transactionType("withdraw")
                .build()));
    }

    @AfterReturning("execution(void transfer(*)) && args(transfer)")
    public void afterTransferSuccess(Transfer transfer) {
        transactionHistoryService.save(transactionHistoryMapper.toEntity(TransactionHistoryDto.builder()
                .transferAmount(transfer.getBalance())
                .account(accountNumberService.getAccountNumber(transfer.getAccountNumber()).getAccount())
                .transactionType("transfer")
                .build()));
    }

}
