package com.phamkhanhhand.kap.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SearchAdjustmentParamDTO {

    private String searchValue;
    private Integer pageIndex;
    private Integer pageSize;

    private String budgetAdjustmentNo;
    private String budgetGroup;
    private String budgetGroupName;
    private String status;
    private String description;
    private String comment;
}
