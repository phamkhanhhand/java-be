package com.phamkhanhhand.kap.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "adm_resources", schema = "dbo")

public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "resource_code")
    private String resourceCode;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "list_scope")
    private String listScope;
}