package com.abhishek.module1introduction.impl;

import com.abhishek.module1introduction.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


//@Primary
@Component
@Qualifier("emailNotification")

// from application.properties if you define a key value pair and that matches
// here then only this bean gets created
//@ConditionalOnProperty(name = "notification.type", havingValue = "email")

public class EmailNotificationService implements NotificationService {

    @Override
    public void send(String message) {
        System.out.println("Sending email... " + message);
    }
}
