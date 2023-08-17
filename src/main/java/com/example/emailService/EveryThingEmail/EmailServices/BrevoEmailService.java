package com.example.emailService.EveryThingEmail.EmailServices;

import com.example.emailService.dtos.request.BrevoEmaiRequest;
import com.example.emailService.dtos.response.BrevoEmailResponse;

public interface BrevoEmailService {
  BrevoEmailResponse brevoMailSender(BrevoEmaiRequest brevoEmaiRequest);
}
