package com.example.emailService.EveryThingEmail.martthinMailSender;

import jakarta.mail.MessagingException;

public interface JavaMailSenderService {

    String sendMail(MailRequest request) throws MessagingException;
}
