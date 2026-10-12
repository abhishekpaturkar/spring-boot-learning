package com.codingshuttle.springboot0To100.hospitalManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    private String status;

    // This is by default FetchType.EAGER;
    // Which will automatically fetch this field
    // To restrict this make it FetchType.LAZY;
//    @ManyToOne(fetch = FetchType.LAZY)4
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Patient patient;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Doctor doctor;
}
