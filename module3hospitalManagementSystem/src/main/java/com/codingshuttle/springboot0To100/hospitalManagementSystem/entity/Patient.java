package com.codingshuttle.springboot0To100.hospitalManagementSystem.entity;

import com.codingshuttle.springboot0To100.hospitalManagementSystem.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@ToString
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    private String email;

    private String gender;

    @Enumerated(value = EnumType.STRING)
    private BloodGroupType bloodGroup;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // When parent updated then this child will also update
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OneToOne(cascade = CascadeType.ALL) // This will take care of everything (delete child as well when parent deleted)
    @JoinColumn(name = "patient_insurance") // This Annotation should be on Owning side
    private Insurance insurance; // owning side

    // We we try to delete the patient it will automatically delete all his appointment
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL) // inverse side
    private Set<Appointment> appointment = new HashSet<>();
}