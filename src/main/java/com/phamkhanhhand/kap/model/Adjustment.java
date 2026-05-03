package com.phamkhanhhand.kap.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "bud_budget_adjustments")
public class Adjustment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bud_budget_adjustments")
    @Column(name = "budget_adjustment_id")  // Cột trong DB
    private Long budgetAdjustmentID;

    @Column(name = "budget_adjustment_no")
    private String budgetAdjustmentNo;

    @Column(name = "budget_group")
    private String budgetGroup;

    @Column(name = "period")
    private String period;

    @Column(name = "status")
    private String status;

    @Column(name = "approval_username")
    private String approvalUsername;

    @Column(name = "description")
    private String description;

    @Column(name = "comment")
    private String comment;


    @Column(name = "total_amount")
    private BigDecimal totalAmount;


    @Column(name = "approval_total_amount")
    private BigDecimal approvalTotalAmount;


    @Column(name = "created_by")
    private String createdBy;


    @Column(name = "modified_by")
    private String modifiedBy;


    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

}
