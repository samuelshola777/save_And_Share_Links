package com.example.emailService.services;

import com.example.emailService.EveryThingEmail.EmailServices.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class EmailServiceTest {
    @Autowired
    EmailService emailService;

    @Test
    void sendSimpleMailMessage() {
        assertDoesNotThrow(()->{
            emailService.sendHtmlEMail("boenshe","samuelshola14@gmail.com","samuelshola14@gmail.com");}
        );
    }
}