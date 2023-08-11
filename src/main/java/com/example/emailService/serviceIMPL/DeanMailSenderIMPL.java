package com.example.emailService.serviceIMPL;

import com.example.emailService.data.model.MailInfo;
import com.example.emailService.dtos.request.EmailRequest;
import com.example.emailService.services.DeanMailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
@Service
public class DeanMailSenderIMPL implements DeanMailSenderService {
    @Value("${MAIL_URL}")
    private String mailUrl;

    @Value("${DEAN_API_KEY}")
    private String apiKey;

    @Value("${APP_NAME}")
    private String appName;

    @Value("${APP_EMAIL}")
    private String appEmail;
    @Override
    public String sendMail(EmailRequest emailRequest) {
        emailRequest.setSender(
                new MailInfo(appName, appEmail)
        );

        return WebClient.builder()
                .baseUrl(mailUrl)
                .defaultHeader("api-key", apiKey)
                .build()
                .post()
                .bodyValue(emailRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
