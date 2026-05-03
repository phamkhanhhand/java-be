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
@Table(name = "adm_flex_values")
public class FlexValue {

    @Id
    @Column(name = "flex_value_id")
    private Long flexValueId;
    @Column(name = "flex_value_set_id")
    private Long flexValueSetId;
    @Column(name = "flex_value")
    private String flexValue;
    @Column(name = "flex_value_name")
    private String flexValueName;
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
    @Column(name = "attribute1")
    private String attribute1;
    @Column(name = "attribute2")
    private String attribute2;
    @Column(name = "attribute3")
    private String attribute3;
    @Column(name = "attribute4")
    private String attribute4;
    @Column(name = "attribute5")
    private String attribute5;
    @Column(name = "attribute6")
    private String attribute6;
    @Column(name = "attribute7")
    private String attribute7;
    @Column(name = "attribute8")
    private String attribute8;
    @Column(name = "attribute9")
    private String attribute9;
    @Column(name = "attribute10")
    private String attribute10;
    @Column(name = "attribute11")
    private String attribute11;
    @Column(name = "attribute12")
    private String attribute12;
    @Column(name = "attribute13")
    private String attribute13;
    @Column(name = "attribute14")
    private String attribute14;
    @Column(name = "attribute15")
    private String attribute15;
    @Column(name = "attribute_label1")
    private String attributeLabel1;
    @Column(name = "attribute_label2")
    private String attributeLabel2;
    @Column(name = "attribute_label3")
    private String attributeLabel3;
    @Column(name = "attribute_label4")
    private String attributeLabel4;
    @Column(name = "attribute_label5")
    private String attributeLabel5;
    @Column(name = "attribute_label6")
    private String attributeLabel6;
    @Column(name = "attribute_label7")
    private String attributeLabel7;
    @Column(name = "attribute_label8")
    private String attributeLabel8;
    @Column(name = "attribute_label9")
    private String attributeLabel9;
    @Column(name = "attribute_label10")
    private String attributeLabel10;
    @Column(name = "attribute_label11")
    private String attributeLabel11;
    @Column(name = "attribute_label12")
    private String attributeLabel12;
    @Column(name = "attribute_label13")
    private String attributeLabel13;
    @Column(name = "attribute_label14")
    private String attributeLabel14;
    @Column(name = "attribute_label15")
    private String attributeLabel15;
    @Column(name = "ref_id")
    private Long refId;
    @Column(name = "allowed_each_period")
    private String allowedEachPeriod;

}
