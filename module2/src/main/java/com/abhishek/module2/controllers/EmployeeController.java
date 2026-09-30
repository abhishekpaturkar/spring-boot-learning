package com.abhishek.module2.controllers;

import com.abhishek.module2.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController

// All the api in this class will start from employees.
// /employees will be the parent
@RequestMapping(path = "/employees")
public class EmployeeController {

    // PathVariables -> Essentials parameters (eg. employees/123)
    // here employeeId is mandatory which is required to retrieve information
    @GetMapping(path = "/{employeeId}")

    // You don't need to everytime mention the same name in url param and method param.
    // If you want to use different name then we need to map
    // @PathVariable(name = "employeeId") Long id
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId) {
        return new EmployeeDTO(employeeId, "Abhishek", "abhishek@gmail.com", 23,
                LocalDate.of(2025, 8, 11), true);

    }

    // RequestParam -> Optional variables
    // requiredFalse -> not mandatory field
    @GetMapping
    public String getAllEmployees(@RequestParam Integer age,
                                  @RequestParam(required = false) String sortBy) {
        return "Hi age " + age + "and Sort by " + sortBy;
    }
}
