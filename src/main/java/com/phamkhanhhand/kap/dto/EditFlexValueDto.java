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

public class EditFlexValueDto extends BaseDto {

    private Long flexValueSetId;

    private Long flexValueId;

    private String flexValue;

    private String flexValueName;

    private String enableFlag;

    private String period;

    private String description;

    private String flexValueCode;
}