package com.example.emailService.serviceIMPL;

import com.example.emailService.Exception.EmailException;
import com.example.emailService.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailServiceIMPL implements EmailService {
    public static final String NEW_USER_ACCOUNT_VERIFICATION = "New_USER_ACCOUNT_VERIFICATION";

   private final JavaMailSender javaMailSender;

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;


    @Override
    public void sendSimpleMailMessage(String name, String to, String token) {
        try{
    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
    message.setFrom(fromEmail);
    message.setTo(to);
    message.setText("hey this is working hahahaha");
    javaMailSender.send(message);

        }catch (Exception exception){
            System.out.println("this is  execption -->  "+exception.getMessage());
            throw new EmailException("couldn't sent email");
        }

    }


    @Override
    public void sendMimeMessageWithAttachments(String name, String to, String token) {

    }

    @Override
    public void sendMimeMessageWithEmbeddedImage(String name, String to, String token) {

    }

    @Override
    public void sendMimeMessageWithEmbeddedFiles(String name, String to, String token) {

    }

    @Override
    public void sendHtmlEMail(String name, String to, String token) {

    }

    @Override
    public void sendHtmlEmailWithEmailEmbeddedFiles(String name, String to, String token) {

    }
}
