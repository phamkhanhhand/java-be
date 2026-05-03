package com.phamkhanhhand.kap.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "bud_budget_adjustment_details")
public class AdjustmentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bud_budget_adjustment_details")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adjustment_seq")
//    @SequenceGenerator(name = "adjustment_seq", sequenceName = "seq_bud_budget_adjustment_details", allocationSize = 1)
    //else ALTER SEQUENCE bud.seq_bud_budget_adjustment_details INCREMENT BY 50;

    @Column(name = "budget_adjustment_detail_id")  // Cột trong DB
    private Long budgetAdjustmentDetailID;


    @Column(name = "budget_adjustment_id")  // Cột trong DB
    private Long budgetAdjustmentID;


    @Column(name = "parent_id")
    private String parentID;

    @Column(name = "parent_flag")
    private String parentFlag;

    @Column(name = "adjustment_type")
    private String adjustmentType;

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

    @Column(name = "approved_amount")
    private BigDecimal approvedAmount;

    @Column(name = "description")
    private String description;

    @Column(name = "comment")
    private String comment;

    @Column(name = "change_type")
    private String changeType;

    @Column(name = "approved_change_type")
    private String approvedChangeType;

//
//
//    @Column(name = "created_by")
//    private String createdBy;
//
//
//    @Column(name = "modified_by")
//    private String modifiedBy;
//
//
//    @Column(name = "created_date")
//    private Date createdDate;
//
//    @Column(name = "modified_date")
//    private Date modifiedDate;

}
