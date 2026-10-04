package com.abhishek.module2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;

    // As the isActive field is coming null everytime
    // The Jackson is adding is in front of the field as it is a boolean type
    // so we simply tell Jackson that the property name
    // Serialization
    @JsonProperty("isActive")
    private Boolean isActive;
}
