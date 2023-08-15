package com.example.emailService.EveryThingEmail.EmailServiceIMPs;

import com.example.emailService.martthinMailSender.MailRequest;
import com.example.emailService.EveryThingEmail.EmailServices.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceIMPL implements EmailService {


    @Override
    public String friendRequestMailSender(MailRequest request) throws MessagingException {
        return null;
    }
}
