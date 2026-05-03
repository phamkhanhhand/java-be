package com.phamkhanhhand.kap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BudgetPlanHistoryDTO {

    private Long budgetPlanHistoryId;
    private Long budgetPlanId;
    private String budgetLine;
    private String fromStatus;
    private String toStatus;
    private String comment;
    private String createdBy;
    private Date createdDate;
}
