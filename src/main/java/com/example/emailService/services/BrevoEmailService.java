package com.example.emailService.services;

import com.example.emailService.dtos.request.BrevoEmaiRequest;

public interface BrevoEmailService {
  String brevoMailSender(BrevoEmaiRequest brevoEmaiRequest);
}
