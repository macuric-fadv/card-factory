package com.marsus.demo.cardfactory.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Base entity class, holding common entity properties
 */
@Data
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class BaseEntity {

    private static final String APP_USER = "cc_factory_app";

    @Id
    @SequenceGenerator(name = "SEQ_ID_GENERATOR", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_ID_GENERATOR")
    protected Long id;

    protected String createdBy;

    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdDate;

    protected String lastModifiedBy;

    protected LocalDateTime lastModifiedDate;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        createdBy = APP_USER;
    }

    @PreUpdate
    protected void onUpdate() {
        lastModifiedDate = LocalDateTime.now();
        lastModifiedBy = APP_USER;
    }
}
