package com.abhishek.module7Testing.repositories;

import com.abhishek.module7Testing.TestingContainerConfiguration;
import com.abhishek.module7Testing.entities.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(TestingContainerConfiguration.class)
// DataJpaTest is used to test JPA repositories.
@DataJpaTest
// @DataJpaTest runs each test case in transaction and rolls back the transaction after each test case. So, we don't need to clean up the database after each test case.
// As @DataJpaTest automatically configures an in-memory database, we don't need below line.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .email("john.doe@example.com")
                .name("John Doe")
                .salary(50000L)
                .build();
    }

    @Test
    void testFindByEmail_whenEmailIsValid_thenReturnEmployee() {
        // Arrange, Given
        employeeRepository.save(employee);

        // Act, When
        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail());

        // Assert, Then
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList).isNotEmpty();
        Assertions.assertThat(employeeList.getFirst().getEmail()).isEqualTo(employee.getEmail());
    }

    @Test
    void testFindByEmail_whenEmailIsInvalid_thenReturnEmptyEmployeeList() {
        // Arrange, Given
        String email = "notPresent123@example.com";

        // Act, When
        List<Employee> employeeList = employeeRepository.findByEmail(email);

        // Assert, Then
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList).isEmpty();
    }
}