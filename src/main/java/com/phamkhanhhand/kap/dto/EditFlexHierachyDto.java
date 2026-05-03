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
public class EditFlexHierachyDto {

    private Long currentID;

    private List<Long> listAddId;

    private List<Long> listRemoveId;

    private Boolean addChild;
}