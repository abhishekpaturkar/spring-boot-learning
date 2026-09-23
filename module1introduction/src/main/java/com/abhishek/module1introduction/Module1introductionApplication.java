package com.abhishek.module1introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {

	// Field Injection (Avoid in production)
	// as we are creating a field notificationService where the dependency gets injected
//	@Autowired
//	NotificationService notificationService;

	// Constructor Injection (Recommended)
	final NotificationService notificationService;
	public Module1introductionApplication(@Qualifier("emailNotification") NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Module1introductionApplication.class, args);
	}

	// Method from CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		notificationService.send("hello");
	}
}
