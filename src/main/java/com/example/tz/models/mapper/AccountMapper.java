package com.example.tz.models.mapper;

import com.example.tz.models.dto.AccountDto;
import com.example.tz.models.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    Account toEntity(AccountDto accountDto);

    AccountDto toDto(Account account);

    List<AccountDto> toDtoList(List<Account> accountList);

}
