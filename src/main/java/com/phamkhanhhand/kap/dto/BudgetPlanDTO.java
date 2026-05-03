package com.phamkhanhhand.kap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BudgetPlanDTO {
    private Long budgetPlanId;
    private String period;
    private String budgetLine;
    private String status;
    private String createdBy;
    private LocalDateTime createdDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;
    private List<BudgetPlanDetailDTO> listBudgetPlanDetail;
}
