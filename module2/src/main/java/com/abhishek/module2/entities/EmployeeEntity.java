package com.abhishek.module2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

// Creates Employee Table inside DB
@Entity
// Getter and Setter from lombok dependency for auto getters and setter
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "employees") // Defining the table name (By Default it will take class name)
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Create auto increment sequence
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean isActive;

}
