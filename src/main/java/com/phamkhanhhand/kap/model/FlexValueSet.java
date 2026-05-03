package com.phamkhanhhand.kap.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "adm_flex_value_sets")
public class FlexValueSet {

    @Id
    @Column(name = "flex_value_set_id")
    private Long flexValueSetId;
    @Column(name = "flex_value_set_code")
    private String flexValueSetCode;
    @Column(name = "flex_value_set_name")
    private String flexValueSetName;
    @Column(name = "enable_flag")
    private String enableFlag;
    @Column(name = "period")
    private String period;
    @Column(name = "description")
    private String description;
    @Column(name = "edit_version")
    private String editVersion;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "modified_date")
    private Date modifiedDate;

}
