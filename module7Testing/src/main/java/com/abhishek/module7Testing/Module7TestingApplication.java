package com.abhishek.module7Testing;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class Module7TestingApplication implements CommandLineRunner {

//	private final DataService dataService;

	@Value("${my.variable}")
	private String myVariable;

	public static void main(String[] args) {
		SpringApplication.run(Module7TestingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("My Variable from application.properties: " + myVariable);

//		System.out.println("Data from DataService: " + dataService.getData());
	}

}
