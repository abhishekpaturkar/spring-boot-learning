package com.abhishek.module7Testing.controllers;

import com.abhishek.module7Testing.TestingContainerConfiguration;
import com.abhishek.module7Testing.dto.EmployeeDto;
import com.abhishek.module7Testing.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWebTestClient(timeout = "100000")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestingContainerConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AbstractIntegrationTest {

    @Autowired
    WebTestClient webTestClient; // Package private

    Employee testEmployee = Employee.builder()
            .name("John Doe")
                .email("john.doe@example.com")
                .salary(50000L)
                .build();

    EmployeeDto testEmployeeDto = EmployeeDto.builder()
            .name("John Doe")
                .email("john.doe@example.com")
                .salary(50000L)
                .build();
}
