package com.phamkhanhhand.kap.dto.mapper;

import com.phamkhanhhand.kap.dto.AdjustmentDTO;
import com.phamkhanhhand.kap.model.Adjustment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdjustmentMapper {
    AdjustmentDTO toDTO(Adjustment balance);
    Adjustment toEntity(AdjustmentDTO dto);
}
