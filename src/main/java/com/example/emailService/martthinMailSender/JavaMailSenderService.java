package com.example.emailService.martthinMailSender;

import jakarta.mail.MessagingException;

public interface JavaMailSenderService {

    String sendMail(MailRequest request) throws MessagingException;
}
