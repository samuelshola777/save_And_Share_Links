package com.example.emailService.services;

import com.example.emailService.data.model.BrevoEmailReceiver;
import com.example.emailService.data.model.BrevoSender;
import com.example.emailService.dtos.request.BrevoEmaiRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class BrevoEmailServiceTest {
@Autowired
BrevoEmailService brevoEmailService;
BrevoEmaiRequest brevoEmaiRequest;
BrevoSender brevoSender;

BrevoEmailReceiver brevoEmailReceiver;

    @BeforeEach
    void setUp() {
  brevoSender = new BrevoSender();
  brevoSender.setName("samuel shola");
  brevoSender.setEmail("samuelshola14@gmail.com");

  brevoEmailReceiver = new BrevoEmailReceiver();
  brevoEmailReceiver.setEmail("samuelshola14@gmail.com");
  brevoEmailReceiver.setName("boneshaker");
        List<BrevoEmailReceiver> brevoEmaiRequestArrayList = new ArrayList<>();
        brevoEmaiRequestArrayList.add(brevoEmailReceiver);

        brevoEmaiRequest = new BrevoEmaiRequest();
        brevoEmaiRequest.setRecipients(brevoEmaiRequestArrayList);
        brevoEmaiRequest.setSender(brevoSender);
        brevoEmaiRequest.setTextContent("i've been through alot trying to get this email service to work and only God can help me");
        brevoEmaiRequest.setSubject("trying to send mail");

    }

    @Test
    void brevoMailSender() {
        assertDoesNotThrow(()->{ brevoEmailService.brevoMailSender(brevoEmaiRequest);});

    }
}