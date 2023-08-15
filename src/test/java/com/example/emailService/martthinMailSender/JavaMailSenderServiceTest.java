package com.example.emailService.martthinMailSender;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JavaMailSenderServiceTest {
@Autowired
JavaMailSenderService javaMailSenderService;

private MailRequest mailRequest;
    @BeforeEach
    void setUp() {
mailRequest = new MailRequest();
        mailRequest.setTo("boneshaker896@gmail.com");
        mailRequest.setSubject("another try from marthins semi colon");
        mailRequest.setMessage("after to one nah to calm down " +
                "think about ma life and why i dey get issues with mail sending");

    }

    @Test
    void sendMail() throws MessagingException {
        javaMailSenderService.sendMail(mailRequest);
    }
}