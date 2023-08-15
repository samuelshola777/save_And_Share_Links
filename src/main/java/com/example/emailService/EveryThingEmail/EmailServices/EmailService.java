package com.example.emailService.EveryThingEmail.EmailServices;

import com.example.emailService.martthinMailSender.MailRequest;
import jakarta.mail.MessagingException;

public interface EmailService {
    String friendRequestMailSender(MailRequest request) throws MessagingException;
}
