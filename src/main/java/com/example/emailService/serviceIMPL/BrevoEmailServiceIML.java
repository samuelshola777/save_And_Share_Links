package com.example.emailService.serviceIMPL;

import com.example.emailService.AppConfig.BrevoConfig;
import com.example.emailService.dtos.request.BrevoEmaiRequest;
import com.example.emailService.services.BrevoEmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@AllArgsConstructor
public class BrevoEmailServiceIML implements BrevoEmailService {
private final BrevoConfig brevoConfig;

    @Override
    public String brevoMailSender(BrevoEmaiRequest brevoEmaiRequest) {
        String brevoMailAddress = "https://api.brevo.com/v3/smtp/email";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", brevoConfig.getMailApiKey());
        headers.set("Content-Type", "application/json");
        HttpEntity<BrevoEmaiRequest> request =
                new HttpEntity<>(brevoEmaiRequest, headers);


                restTemplate.postForEntity(brevoMailAddress, request, BrevoEmaiRequest.class);
                return "Email sent successfully";
    }
}
