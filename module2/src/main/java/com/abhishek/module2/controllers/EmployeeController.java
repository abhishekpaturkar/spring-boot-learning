package com.abhishek.module2.controllers;

import com.abhishek.module2.dto.EmployeeDTO;
import com.abhishek.module2.entities.EmployeeEntity;
import com.abhishek.module2.repositories.EmployeeRepository;
import com.abhishek.module2.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController

@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);

    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy) {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee) {
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping
    public String updateEmployeeById() {
        return "Hello From Put Controller";
    }
}
