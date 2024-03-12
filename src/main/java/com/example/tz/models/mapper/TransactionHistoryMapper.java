package com.example.tz.models.mapper;

import com.example.tz.models.dto.TransactionHistoryDto;
import com.example.tz.models.entity.TransactionHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionHistoryMapper {

    @Mapping(target = "id", ignore = true)
    TransactionHistory toEntity(TransactionHistoryDto transactionHistoryDto);

    TransactionHistoryDto toDto(TransactionHistory transactionHistory);

    List<TransactionHistoryDto> toDtoList(List<TransactionHistory> transactionHistoryList);

}
