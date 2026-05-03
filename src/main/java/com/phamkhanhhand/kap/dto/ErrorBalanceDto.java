package com.phamkhanhhand.kap.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class ErrorBalanceDto {

    @JsonProperty("CheckRs")
    private String checkRs;

//    @JsonProperty("CheckReason")
    private String checkReason;

    @JsonProperty("remainning_amount")
    private BigDecimal remainningAmount;

}