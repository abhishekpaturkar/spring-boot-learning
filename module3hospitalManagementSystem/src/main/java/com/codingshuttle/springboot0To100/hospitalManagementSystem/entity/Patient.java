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

    // orphan removal -> When the user want to remove the insurance from the patient
    // i.e set the insurance to null, then the previously associated insurance will be deleted
    // This should only be added on the parent side

    // FetchType.EAGER -> this will fetch insurance as well when try to fetch the patient using join

    // FetchType.LAZY -> will not perfrom join call when we try to get the patient

    // For getting insurance we can call patient.getInsurace() this will fetch insurance in separate query
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_insurance") // This Annotation should be on Owning side
    private Insurance insurance; // owning side

    // We we try to delete the patient it will automatically delete all his appointment
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Appointment> appointment = new HashSet<>();
}