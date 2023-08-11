package com.example.emailService.serviceIMPL;

import com.example.emailService.AppConfig.BrevoConfig;
import com.example.emailService.dtos.request.BrevoEmaiRequest;
import com.example.emailService.dtos.response.BrevoEmailResponse;
import com.example.emailService.services.BrevoEmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j

public class BrevoEmailServiceIML implements BrevoEmailService {
    @Autowired
private  BrevoConfig brevoConfig;
@Value("${MAIL_URL}")
 String brevoMailAddress;
    @Override
    public BrevoEmailResponse brevoMailSender(BrevoEmaiRequest brevoEmaiRequest) {

//       pr String brevoMailAddress
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", brevoConfig.getMailApiKey());
        headers.set("Content-Type", "application/json");
        HttpEntity<BrevoEmaiRequest> request =
                new HttpEntity<>(brevoEmaiRequest, headers);


        ResponseEntity<BrevoEmailResponse> response
              =  restTemplate.postForEntity(brevoMailAddress, request, BrevoEmailResponse.class);
        return response.getBody();
    }
}
