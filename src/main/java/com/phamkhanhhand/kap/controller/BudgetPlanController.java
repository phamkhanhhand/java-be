package com.phamkhanhhand.kap.controller;

import com.phamkhanhhand.kap.dto.BudgetPlanDTO;
import com.phamkhanhhand.kap.dto.CommonApprovalResponseDTO;
import com.phamkhanhhand.kap.dto.CommonRequestDTO;
import com.phamkhanhhand.kap.service.BudgetPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/BudgetPlan")
public class BudgetPlanController {
    private final BudgetPlanService budgetPlanService;

    @PostMapping("createPlan")
    public Object createPlan(@RequestBody BudgetPlanDTO budgetPlanDTO){
        return budgetPlanService.create(budgetPlanDTO);
    }

    @GetMapping("fillAllPlan")
    public Object fillAllPlan(){
        return budgetPlanService.findAll();
    }

    @PutMapping("submitPlan")
    public CommonApprovalResponseDTO submitPlan(@RequestBody CommonRequestDTO requestDTO) {
        return budgetPlanService.submit(requestDTO);
    }

    @PutMapping("approvePlan")
    public CommonApprovalResponseDTO approvePlan(@RequestBody CommonRequestDTO requestDTO){
        return budgetPlanService.approve(requestDTO);
    }

    @PutMapping("rejectPlan")
    public CommonApprovalResponseDTO rejectPlan(@RequestBody CommonRequestDTO requestDTO){
        return budgetPlanService.reject(requestDTO);
    }

    @PutMapping("cancelPlan")
    public CommonApprovalResponseDTO cancelPlan(@RequestBody CommonRequestDTO requestDTO){
        return budgetPlanService.cancel(requestDTO);
    }
}
