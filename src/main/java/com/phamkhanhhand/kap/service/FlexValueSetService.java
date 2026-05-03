package com.phamkhanhhand.kap.service;

import com.phamkhanhhand.kap.dto.*;
import com.phamkhanhhand.kap.model.Adjustment;
import com.phamkhanhhand.kap.model.FlexValueSet;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface FlexValueSetService {

    Page<FlexValueSetDto> getPagingFlexValueSet(PageParamDTO request);

    FlexValueSet save(EditFlexValueSetDto dto);

}
