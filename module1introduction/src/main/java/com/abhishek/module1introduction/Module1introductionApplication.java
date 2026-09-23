package com.abhishek.module1introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {

	// Field Injection (Avoid in production)
	// as we are creating a field notificationService where the dependency gets injected
//	@Autowired
//	NotificationService notificationService;

	// Constructor Injection (Recommended)
//	final NotificationService notificationService;
//	public Module1introductionApplication(@Qualifier("emailNotification") NotificationService notificationService) {
//		this.notificationService = notificationService;
//	}

	// So as the application property is set to email only the EmailNotification bean will be inserted
//	final NotificationService notificationService;
//	public Module1introductionApplication(NotificationService notificationService) {
//		this.notificationService = notificationService;
//	}

	// To get all the implementation of NotificationService
	@Autowired
	Map<String, NotificationService> notificationServiceMap = new HashMap<>();

	// Optional Bean (It is optional so will not give error)
//	@Autowired(required = false)
//	NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(Module1introductionApplication.class, args);
	}

	// Method from CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
//		notificationService.send("hello");

		// Use case: When want to send notification using both email and sms
		for(var notificationService : notificationServiceMap.entrySet()) {
			System.out.println(notificationService.getKey());
			notificationService.getValue().send("hello");
		}
	}
}
