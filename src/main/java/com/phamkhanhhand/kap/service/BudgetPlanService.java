package com.phamkhanhhand.kap.service;

import com.phamkhanhhand.kap.dto.BudgetPlanDTO;
import com.phamkhanhhand.kap.dto.CommonApprovalResponseDTO;
import com.phamkhanhhand.kap.dto.CommonRequestDTO;
import com.phamkhanhhand.kap.model.BudgetPlan;

import java.util.List;

public interface BudgetPlanService {

    BudgetPlanDTO create(BudgetPlanDTO budgetPlanDTO);
    List<BudgetPlan> findAll();
    public CommonApprovalResponseDTO submit(CommonRequestDTO requestDTO);
    public CommonApprovalResponseDTO reject(CommonRequestDTO requestDTO);
    public CommonApprovalResponseDTO approve(CommonRequestDTO requestDTO);
    public CommonApprovalResponseDTO cancel(CommonRequestDTO requestDTO);
}
