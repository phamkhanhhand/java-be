package com.phamkhanhhand.kap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "bud_budget_plan_histories")
public class BudgetPlanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_plan_history_id")
    private Long budgetPlanHistoryId;
    @Column(name = "budget_plan_id")
    private Long budgetPlanId;
    @Column(name = "budget_line")
    private String budgetLine;
    @Column(name = "from_status")
    private String fromStatus;
    @Column(name = "to_status")
    private String toStatus;
    @Column(name = "comment")
    private String comment;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    private Date createdDate;

}
