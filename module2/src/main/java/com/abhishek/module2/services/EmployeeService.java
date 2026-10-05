package com.abhishek.module2.services;

import com.abhishek.module2.dto.EmployeeDTO;
import com.abhishek.module2.entities.EmployeeEntity;
import com.abhishek.module2.exceptions.ResourceNotFoundException;
import com.abhishek.module2.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    // Using repository here
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        // We can write business logic
        // to check if user is admin
        // log something
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity employeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        isExistingEmployeeId(employeeId);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        isExistingEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        isExistingEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();

        // To update the object we use ReflectionUtility
        updates.forEach((field, value) -> {
            Field fieldToUpdate = ReflectionUtils.getRequiredField(EmployeeEntity.class, field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, employeeEntity, value);
        });

        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }

    public void isExistingEmployeeId(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
    }
}
