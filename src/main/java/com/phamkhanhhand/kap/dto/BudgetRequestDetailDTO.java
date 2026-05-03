package com.phamkhanhhand.kap.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BudgetRequestDetailDTO {

    private Long balanceID;

    private String segment1;

    private String segment2;

    private String segment3;

    private String segment4;

    private String segment5;

    private String segment6;

    private String segment7;

    private String segment8;

    private String segment9;

    private String segment10;

    private BigDecimal amount;

    private Long headerID;
    private Long detailID;

}
