package com.phamkhanhhand.kap.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class FlexValueSetDto {

    @JsonProperty("flex_value_set_id")
    private Long flexValueSetId;

    @JsonProperty("flex_value_set_code")
    private String flexValueSetCode;

    @JsonProperty("flex_hierarchy_set_id")
    private Long flexHierarchySetId;

    @JsonProperty("flex_value_set_name")
    private String flexValueSetName;

    @JsonProperty("enable_flag")
    private String enableFlag;

    private String period;

    private String description;

//    private List<EditFlexValueDto> detail;

    private List<Long> listDelete;
}