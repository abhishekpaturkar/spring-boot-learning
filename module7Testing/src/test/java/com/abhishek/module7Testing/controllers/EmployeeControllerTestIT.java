package com.abhishek.module7Testing.controllers;

import com.abhishek.module7Testing.dto.EmployeeDto;
import com.abhishek.module7Testing.entities.Employee;
import com.abhishek.module7Testing.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class EmployeeControllerTestIT extends AbstractIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        // Clean up the repository before each test
        employeeRepository.deleteAll();
    }

    @Test
    void testGetEmployeeById_Success() {
        Employee savedEmployee = employeeRepository.save(testEmployee);

        webTestClient.get()
                .uri("/employees/{id}", savedEmployee.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(savedEmployee.getId())
                .jsonPath("$.email").isEqualTo(savedEmployee.getEmail())
                .jsonPath("$.name").isEqualTo(savedEmployee.getName());
//                .value(employeeDto -> {
//                    assertThat(employeeDto.getEmail()).isEqualTo(savedEmployee.getEmail());
//                    assertThat(employeeDto.getId()).isEqualTo(savedEmployee.getId());
//                });
    }

    @Test
    void testGetEmployeeById_Failure() {
        webTestClient.get()
                .uri("/employees/{id}", 1L) // Assuming 999L is a non-existent ID
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCreateEmployee_WhenEmployeeAlreadyExists_ThenThrowException() {
        Employee savedEmployee = employeeRepository.save(testEmployee);

        webTestClient.post()
                .uri("/employees")
                .bodyValue(testEmployeeDto)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void testCreateNewEmployee_WhenEmployeeDoesNotExists_ThenCreateNewEmployee() {
        webTestClient.post()
                .uri("/employees")
                .bodyValue(testEmployeeDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.email").isEqualTo(testEmployee.getEmail())
                .jsonPath("$.name").isEqualTo(testEmployee.getName());
    }

    @Test
    void testUpdateEmployee_WhenEmployeeDoesNotExists_ThenThrowException() {
        webTestClient.put()
                .uri("/employees/{id}", 999L) // Assuming 999L is a non-existent ID
                .bodyValue(testEmployeeDto)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateEmployee_WhenAttemptingToUpdateEmail_ThenThrowException() {
        Employee savedEmployee = employeeRepository.save(testEmployee);

        testEmployeeDto.setName("Abhishek");
        testEmployeeDto.setEmail("abhishek@gmail.com");

        webTestClient.put()
                .uri("/employees/{id}", savedEmployee.getId())
                .bodyValue(testEmployeeDto)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void testUpdateEmployee_WhenEmployeeIsValid_ThenUpdateEmployee() {
        Employee savedEmployee = employeeRepository.save(testEmployee);

        testEmployeeDto.setName("Abhishek");
        testEmployeeDto.setSalary(100L);

        webTestClient.put()
                .uri("/employees/{id}", savedEmployee.getId())
                .bodyValue(testEmployeeDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo(testEmployeeDto.getName())
                .jsonPath("$.salary").isEqualTo(testEmployeeDto.getSalary());
    }

    @Test
    void testDeleteEmployee_WhenEmployeeDoesNotExists_ThenThrowException() {
        webTestClient.delete()
                .uri("/employees/{id}", 999L) // Assuming 999L is a non-existent ID
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteEmployee_WhenEmployeeExists_ThenDeleteEmployee() {
        Employee savedEmployee = employeeRepository.save(testEmployee);

        webTestClient.delete()
                .uri("/employees/{id}", savedEmployee.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody(Void.class);

        webTestClient.delete()
                .uri("/employees/{id}", savedEmployee.getId()) // Assuming 999L is a non-existent ID
                .exchange()
                .expectStatus().isNotFound();
    }
}