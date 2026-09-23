package com.abhishek.module1introduction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// This annotation tells spring use this class to track all the beans in the application
@Configuration
public class AppConfig {

    @Bean
//    @Scope("prototype")
    public PaymentService paymentService(){
        // We are telling spring how this bean will be created
        // Managing the lifecycle is spring's reponsibility
        System.out.println("Payment Service Bean");

        return new PaymentService();

    }
}
