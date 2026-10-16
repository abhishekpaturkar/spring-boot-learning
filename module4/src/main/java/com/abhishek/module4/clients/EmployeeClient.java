package com.abhishek.module4.clients;

import com.abhishek.module4.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long employeeId);

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
}
