package com.abhishek.module2.controllers;

import com.abhishek.module2.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController

// All the api in this class will start from employees.
// /employees will be the parent
@RequestMapping(path = "/employees")
public class EmployeeController {

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId) {
        return new EmployeeDTO(employeeId, "Abhishek", "abhishek@gmail.com", 23,
                LocalDate.of(2025, 8, 11), true);

    }

    @GetMapping
    public String getAllEmployees(@RequestParam Integer age,
                                  @RequestParam(required = false) String sortBy) {
        return "Hi age " + age + "and Sort by " + sortBy;
    }

    // RequestBody -> It is used to bind the HTTP request body with Java object.
    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee) {
        inputEmployee.setId(100);
        return inputEmployee;
    }

    @PutMapping
    public String updateEmployeeById() {
        return "Hello From Put Controller";
    }
}
