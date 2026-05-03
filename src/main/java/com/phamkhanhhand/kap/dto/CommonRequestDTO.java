package com.phamkhanhhand.kap.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CommonRequestDTO {

    private Long id;
    private String comment;
    private String toUsername;

    /*
    * Action: reject/approve
    * enume ApproveAction
     */
    private int action;

    private List<String> toListUsername;
    private String specialArg;
    private Map<String, Object> specialArgList;
}
