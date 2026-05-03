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
@Table(name = "bud_balances")
public class Balance {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bud_balance")

    @Column(name = "balance_id")  // Cột trong DB
    private Long balanceID;

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

    @Column(name = "planning_amount")
    private BigDecimal planning_amount;


    @Column(name = "remainning_amount")
    private BigDecimal remainning_amount;


    @Column(name = "period")
    private String period;


}
