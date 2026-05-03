package com.phamkhanhhand.kap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "bud_budget_plans")
public class BudgetPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_bud_budget_plans")
    @SequenceGenerator(
            name = "seq_bud_budget_plans",
            sequenceName = "seq_bud_budget_plans",
            allocationSize = 1
    )

    @Column(name = "budget_plan_id")
    private Long budgetPlanId;
    @Column(name ="period")
    private String period;
    @Column(name = "budget_line")
    private String budgetLine;
    @Column(name = "status")
    private String status;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
    @PrePersist
    protected void onCreate(){
        this.createdDate=LocalDateTime.now();
        this.modifiedDate=null;
    }
    @PreUpdate
    protected void onUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }
}
