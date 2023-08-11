package com.example.emailService.services;

import com.example.emailService.dtos.request.EmailRequest;

public interface DeanMailSenderService {
    public String sendMail(EmailRequest emailRequest);


}
