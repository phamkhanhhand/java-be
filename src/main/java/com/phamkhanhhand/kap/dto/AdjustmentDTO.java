package com.phamkhanhhand.kap.dto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AdjustmentDTO {

    private Long budgetAdjustmentID;
    private String budgetAdjustmentNo;
    private String budgetGroup;
    private String period;
    private String status;
    private String approvalUsername;
    private String description;
    private String comment;
    private BigDecimal totalAmount;
    private BigDecimal approvalTotalAmount;
    private BigDecimal createdBy;
    private BigDecimal modifiedBy;
    private Date createdDate;
    private Date modifiedDate;
    
}
