package com.codingshuttle.springboot0To100.hospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    String policyNumber;

    @Column(nullable = false, length = 100)
    String provider;

    @Column(nullable = false)
    LocalDate validUntil;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // But this OneToOne will create a column inside insurance as patient_id
    // But we want only single source of truth,
    // So to avoid this we need to define mappedBy inside OneToOne and give the field name which is present in patient table
    // This side in inverse side
    @OneToOne(mappedBy = "insurance")
    @ToString.Exclude
    private Patient patient;

}
