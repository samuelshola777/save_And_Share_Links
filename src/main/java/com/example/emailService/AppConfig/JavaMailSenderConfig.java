package com.example.emailService.AppConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailSenderConfig {
    @Value("${EMAIL_ADDRESS}")
    private String emailAddress;
    @Value("${EMAIL_PASSWORD}")
    private String emailPassword;


    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setProtocol("smtp");
        sender.setUsername(emailAddress);
        sender.setPassword(emailPassword);

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", true);
        mailProperties.put("mail.smtp.timeout",   10000);
        mailProperties.put("mail.smtp.connection-timeout", 10000);
        mailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        sender.setJavaMailProperties(mailProperties);

        return sender;
    }
}
