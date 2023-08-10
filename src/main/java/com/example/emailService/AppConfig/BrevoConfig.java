package com.example.emailService.AppConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrevoConfig {

    @Value("${BREVO_API}")
    private String mailApiKey;
    public String getMailApiKey() {
        return mailApiKey;
    }
}
