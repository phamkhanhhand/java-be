package com.phamkhanhhand.kap.service;

import com.phamkhanhhand.kap.dto.AdjustmentDTO;
import com.phamkhanhhand.kap.dto.SearchAdjustmentParamDTO;
import com.phamkhanhhand.kap.model.Adjustment;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface RequestFormService {

    Page<AdjustmentDTO> search(SearchAdjustmentParamDTO request);
    Optional<Adjustment> getByID(Long id);

    // <editor-fold desc="RequestForm">
//
//    public CommonApprovalResponseDTO submit(CommonRequestDTO requestDTO);
//
//    public CommonApprovalResponseDTO approve(CommonRequestDTO requestDTO);
//
//
//    public CommonApprovalResponseDTO complete(CommonRequestDTO requestDTO);
//

    // </editor-fold>




}
