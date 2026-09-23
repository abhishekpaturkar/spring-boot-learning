package com.abhishek.module1introduction;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

// This will tell spring to treat this class a Bean
@Component
public class PaymentService  {

    public void pay() {
        System.out.println("paying...");
    }

    @PostConstruct
    public void init() {
        System.out.println("init...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy...");
    }
}
