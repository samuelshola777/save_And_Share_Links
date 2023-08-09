package com.example.emailService.services;

public interface EmailService {
    void sendSimpleMailMessage(String name, String to, String token);
    void sendMimeMessageWithAttachments(String name, String to, String token);
    void sendMimeMessageWithEmbeddedImage(String name, String to, String token);
    void sendMimeMessageWithEmbeddedFiles(String name, String to, String token);
    void sendHtmlEMail(String name, String to, String token);
    void sendHtmlEmailWithEmailEmbeddedFiles(String name, String to, String token);
}
