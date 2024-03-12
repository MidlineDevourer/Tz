package com.example.tz.models.mapper;

import com.example.tz.models.dto.AccountNumberDto;
import com.example.tz.models.entity.AccountNumber;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountNumberMapper {

    @Mapping(target = "id", ignore = true)
    AccountNumber toEntity(AccountNumberDto accountNumberDto);

    AccountNumberDto toDto(AccountNumber accountNumber);

}
