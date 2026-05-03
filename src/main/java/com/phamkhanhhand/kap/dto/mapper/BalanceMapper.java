package com.phamkhanhhand.kap.dto.mapper;

import com.phamkhanhhand.kap.dto.BalanceDTO;
import com.phamkhanhhand.kap.model.Balance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceMapper {
    BalanceDTO toDTO(Balance balance);
    Balance toEntity(BalanceDTO dto);
}
