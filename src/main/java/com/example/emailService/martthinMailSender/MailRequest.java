package com.example.emailService.martthinMailSender;

import lombok.Data;

@Data
public class MailRequest {
    private String to;
    private String subject;
    private String message;
    private final String from = "BONESHAKER@gmail.com"; ;
}
