package com.abhishek.module2.dto;

import com.abhishek.module2.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
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

//    @NotNull(message = "Required field in Employee: name") (This can accept "" cause this is not null)
//    @NotEmpty(message = "Name of the Employee cannot be empty") (This can accept "   ", cause the length is greater than 0)
    @NotBlank(message = "Name of the Employee cannot be blank")
    @Size(min = 2, max = 10, message = "Number of characters in name should be in the range: [2, 10]")
    private String name;

    @NotBlank(message = "Email of the Employee cannot be blank")
    @Email(message = "Email should be a valid email")
    private String email;

    @NotNull(message = "Age of the Employee cannot be null")
    @Max(value = 80, message = "Age of Employee cannot be greater than 80")
    @Min(value = 18, message = "Age of Employee cannot be less than 18")
    private Integer age;

    @NotBlank(message = "Role of the Employee cannot be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of Employee can be USER or ADMIN")
    // Custom annotations
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "Salary of Employee cannot be null")
    @Positive(message = "Salary of Employee should be positive")
    // 6 digits and 2 digits after point
    @Digits(integer = 6, fraction = 2, message = "The salary should be in the format XXXXXX.XX")
    @DecimalMin(value = "100.50")
    @DecimalMax(value = "100000.99")
    private Double salary;

    @PastOrPresent(message = "Date of Joining filed in employee cannot be in future")
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    @AssertTrue(message = "Employee should be active")
    private Boolean isActive;
}
