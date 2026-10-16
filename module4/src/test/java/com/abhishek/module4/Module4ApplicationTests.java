package com.abhishek.module4;

import com.abhishek.module4.clients.EmployeeClient;
import com.abhishek.module4.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Module4ApplicationTests {

	@Autowired
	EmployeeClient employeeClient;

	@Test
	@Order(3)
	void getAllEmployeesTest() {
		List<EmployeeDTO> employeeDTOList = employeeClient.getAllEmployees();
		System.out.println(employeeDTOList);
	}

	@Test
	@Order(2)
	void getEmployeeByIdTest() {
		EmployeeDTO employeeDTO = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTO);
	}

	@Test
	@Order(1) // order of the test case that will run
	void createEmployeeTest() {
		EmployeeDTO employeeDTO = new EmployeeDTO(
				null, "Abhishek", "abhi@x.com", 20, "ADMIN", 5000.0, LocalDate.now(), true
		);

		EmployeeDTO saved = employeeClient.createEmployee(employeeDTO);
		System.out.println(saved);
	}

}
