package com.phamkhanhhand.kap.service;

import com.phamkhanhhand.kap.dto.AdjustmentDTO;
import com.phamkhanhhand.kap.dto.CommonApprovalResponseDTO;
import com.phamkhanhhand.kap.dto.CommonRequestDTO;

import java.util.List;


public interface AdjustmentService {

    List<AdjustmentDTO> getAll();

    AdjustmentDTO getByID(Long id);


    public CommonApprovalResponseDTO submit(CommonRequestDTO requestDTO);

    public CommonApprovalResponseDTO approve(CommonRequestDTO requestDTO);


    public CommonApprovalResponseDTO complete(CommonRequestDTO requestDTO);





}
