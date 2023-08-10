package com.example.emailService.services;

import com.example.emailService.dtos.request.BrevoEmaiRequest;
import com.example.emailService.dtos.response.BrevoEmailResponse;

public interface BrevoEmailService {
  BrevoEmailResponse brevoMailSender(BrevoEmaiRequest brevoEmaiRequest);
}
