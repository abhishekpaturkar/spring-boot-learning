package com.abhishek.module2.controllers;

import com.abhishek.module2.dto.EmployeeDTO;
import com.abhishek.module2.entities.EmployeeEntity;
import com.abhishek.module2.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController

@RequestMapping(path = "/employees")
public class EmployeeController {
    // NOTE: The below code is not recommended, Service layer should be in the middle
    // So the Controller cannot directly talk with the Entity.


    // NOTE: Controller should not directly connect with Repo, Service should be in the middle
    // but for sake of the class we are importing repo directly

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);

    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sortBy) {
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee) {
        return employeeRepository.save(inputEmployee);
    }

    @PutMapping
    public String updateEmployeeById() {
        return "Hello From Put Controller";
    }
}
