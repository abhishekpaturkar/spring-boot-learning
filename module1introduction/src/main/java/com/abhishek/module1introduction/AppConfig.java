package com.abhishek.module1introduction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// This annotation tells spring use this class to track all the beans in the application
@Configuration
public class AppConfig {

    @Bean
    public PaymentService paymentService(){
        return new PaymentService();
    }
}
