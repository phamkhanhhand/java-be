package com.phamkhanhhand.kap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "bud_budget_plan_details")
public class BudgetPlanDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_bud_budget_plan_details")
    @SequenceGenerator(
            name = "seq_bud_budget_plan_details",
            sequenceName = "seq_bud_budget_plan_details",
            allocationSize = 1
    )
    @Column(name = "budget_plan_detail_id")
    private Long budgetPlanDetailId;
    @Column(name = "budget_plan_id")
    private Long budgetPlanId;
    @Column(name = "segment1")
    private String segment1;
    @Column(name = "segment2")
    private String segment2;
    @Column(name = "segment3")
    private String segment3;
    @Column(name = "segment4")
    private String segment4;
    @Column(name = "segment5")
    private String segment5;
    @Column(name = "segment6")
    private String segment6;
    @Column(name = "segment7")
    private String segment7;
    @Column(name = "segment8")
    private String segment8;
    @Column(name = "segment9")
    private String segment9;
    @Column(name = "segment10")
    private String segment10;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "amount_period_second")
    private BigDecimal amountPeriodSecond;
}
