package com.phamkhanhhand.kap.dto;


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
public class CommonApprovalResponseDTO {

    private Long id;
    private String errorMessage;
    private String status;

    private List<String> MessageList;

}
