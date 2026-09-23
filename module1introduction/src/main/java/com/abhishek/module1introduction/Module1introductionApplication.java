package com.abhishek.module1introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	CommandLineRunner
	-> a functional interface in Spring Boot used to execute custom code
	once after the Spring application context has been fully initialized
	but before the application is completely started
 */

@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {
	// To inject the bean here this annotation is used
	@Autowired
	PaymentService paymentService = null;

	public static void main(String[] args) {
		SpringApplication.run(Module1introductionApplication.class, args);
	}

	// Method from CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		paymentService.pay();
	}
}
