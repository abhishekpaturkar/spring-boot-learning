package com.abhishek.module7Testing;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
@Slf4j
class Module7TestingApplicationTests {

	// Only run once before all tests in the class
	@BeforeAll
	static void setupAll() {
		log.info("Starting the test class, setting up config");
	}

	// Only run once after all tests in the class
	@AfterAll
	static void tearDownAll() {
		log.info("Tearing down the test class, cleaning up config");
	}

	// Run before each test method
	@BeforeEach
	void setup() {
		log.info("Starting the method, setting up config");
	}

	// Run after each test method
	@AfterEach
	void tearDown() {
		log.info("Tearing down the method, cleaning up config");
	}

	@Test
//	@Disabled
	void testNumberOne() {
		log.info("Test one is running");
		int a = 5;
		int b = 10;
		int result = addTwoNumbers(a, b);

		// This is from JUNIT Assertion class
//		Assertions.assertEquals(15, result);

		// AssertJ assertion
		// We can chain multiple assertions together
		Assertions.assertThat(result)
				.isEqualTo(15)
				.isCloseTo(14, Offset.offset(1));
	}

	@Test
//	@DisplayName("displayTestNameTwo")
	void testNumberTwo() {
		Assertions.assertThat("Apple")
				.isEqualTo("Apple")
				.startsWith("App")
				.endsWith("le")
				.contains("pl");
	}

	@Test
	void testNumberThree() {
		int a = 10;
		int b = 0;

		Assertions.assertThatThrownBy(() -> divideTwoNumbers(a, b))
				.isInstanceOf(ArithmeticException.class)
				.hasMessageContaining("/ by zero");
	}

	int addTwoNumbers(int a, int b) {
		return a + b;
	}

	double divideTwoNumbers(int a, int b) {
		if (b == 0) {
			throw new ArithmeticException("/ by zero");
		}

		return (double) a / b;
	}
}
