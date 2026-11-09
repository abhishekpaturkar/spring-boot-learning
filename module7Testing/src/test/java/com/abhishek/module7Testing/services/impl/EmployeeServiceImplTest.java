package com.abhishek.module7Testing.services.impl;

import com.abhishek.module7Testing.TestingContainerConfiguration;
import com.abhishek.module7Testing.dto.EmployeeDto;
import com.abhishek.module7Testing.entities.Employee;
import com.abhishek.module7Testing.exceptions.ResourceNotFoundException;
import com.abhishek.module7Testing.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@Import(TestingContainerConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Spy // using the actual modelMapper bean instead of mocking it.
    // As modelMapper is already tested so we don't need to mock it.
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee mockEmployee;
    private EmployeeDto mockEmployeeDto;
    @BeforeEach
    void setup() {

        mockEmployee = Employee.builder()
                .id(1L)
                .name("John Doe")
                .email("john.doe@example.com")
                .salary(50000L)
                .build();

        mockEmployeeDto = modelMapper.map(mockEmployee, EmployeeDto.class);

    }

    @Test
    void testGetEmployeeById_WhenEmployeeIdIsPresent_ThenReturnEmployeeDto() {
        // Assign
        Long id = mockEmployee.getId();

        // Stubbing
        when(employeeRepository.findById(id))
                .thenReturn(Optional.of(mockEmployee));

        // Act
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);

        // Assert
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getId()).isEqualTo(id);
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployee.getEmail());
        assertThat(employeeDto.getName()).isEqualTo(mockEmployee.getName());
        assertThat(employeeDto.getSalary()).isEqualTo(mockEmployee.getSalary());

        // Checking is this method called or not. If this method is not called then it means our service layer is not working properly.
        verify(employeeRepository).findById(id);

        verify(employeeRepository, only()).findById(id);
    }

    @Test
    void testGetEmployeeById_WhenEmployeeIsNotPresent_ThenThrowException() {
        // Assign
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> employeeService.getEmployeeById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");

        // Verify
        verify(employeeRepository).findById(1L);
    }

    @Test
    void testCreateNewEmployee_WhenValidEmployee_ThenCreateNewEmployee() {
        // Assign
        when(employeeRepository.findByEmail(anyString()))
                .thenReturn(List.of());

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(mockEmployee);

        // Act
        EmployeeDto employeeDto = employeeService.createNewEmployee(mockEmployeeDto);

        // Assert
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getId()).isEqualTo(mockEmployeeDto.getId());
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployeeDto.getEmail());

        // Verify
        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(captor.capture());

        Employee capturedEmployee = captor.getValue();
        assertThat(capturedEmployee.getEmail()).isEqualTo(mockEmployee.getEmail());
    }

    @Test
    void testCreateNewEmployee_WhenAttemptingToCreateEmployeeWithExistingEmail_ThenThrowException() {
        // Assign
        when(employeeRepository.findByEmail(anyString()))
                .thenReturn(List.of(mockEmployee));

        // Act & Assert
        assertThatThrownBy(() -> employeeService.createNewEmployee(mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Employee already exists with email: " + mockEmployeeDto.getEmail());

        // Verify
        verify(employeeRepository).findByEmail(mockEmployeeDto.getEmail());
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void testUpdateEmployee_WhenValidEmployee_ThenUpdateEmployee() {
        // Assign
        Long id = mockEmployeeDto.getId();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee));

        mockEmployeeDto.setName("Updated Name");
        mockEmployeeDto.setSalary(100L);

        Employee newEmployee = modelMapper.map(mockEmployeeDto, Employee.class);
        when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);

        // Act
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(id, mockEmployeeDto);

        // Assert
        assertThat(updatedEmployeeDto).isNotNull();
        assertThat(updatedEmployeeDto).isEqualTo(mockEmployeeDto);

        // Verify
        verify(employeeRepository).findById(id);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void testUpdateEmployee_WhenEmployeeDoesNotExist_ThenThrowException() {
        // Assign
        Long id = mockEmployee.getId();
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> employeeService.updateEmployee(id, mockEmployeeDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: " + id);

        // Verify
        verify(employeeRepository).findById(id);
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void testUpdateEmployee_WhenAttemptingToUpdateEmail_ThenThrowException() {
        // Assign
        Long id = mockEmployee.getId();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee));
        mockEmployeeDto.setEmail("updated@gmail.com");
        mockEmployeeDto.setName("Updated Name");

        // Assert
        assertThatThrownBy(() -> employeeService.updateEmployee(id, mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("The email of the employee cannot be updated");

        // Verify
        verify(employeeRepository).findById(id);
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void testDeleteEmployee_WhenEmployeeExists_ThenDeleteEmployee() {
        // Assign
        Long id = mockEmployee.getId();
        when(employeeRepository.existsById(id)).thenReturn(true);

        // Assert
        assertThatCode(() -> employeeService.deleteEmployee(id)).doesNotThrowAnyException();

        // Verify
        verify(employeeRepository).existsById(id);
        verify(employeeRepository).deleteById(id);
    }

    @Test
    void testDeleteEmployee_WhenEmployeeDoesNotExist_ThenThrowException() {
        // Assign
        Long id = mockEmployee.getId();
        when(employeeRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThatThrownBy(() -> employeeService.deleteEmployee(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: " + id);

        // Verify
        verify(employeeRepository).existsById(id);
        verify(employeeRepository, never()).deleteById(any());
    }
}