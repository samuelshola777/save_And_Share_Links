package com.example.emailService.EveryThingEmail.EmailServices;

import com.example.emailService.dtos.request.EmailRequest;

public interface DeanMailSenderService {
    public String sendMail(EmailRequest emailRequest);


}
