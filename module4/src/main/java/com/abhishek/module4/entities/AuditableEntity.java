package com.abhishek.module4.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // this will add the field of this class to child class where it extends
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity {

    // Auditing: This will automatically fill by the JPA
    @CreatedDate
    @Column(nullable = false, updatable = false) // Enforce not to be updated
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;
}
