package com.example.emailService.EveryThingEmail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

//import javax.mail.Authenticator;
//import javax.mail.PasswordAuthentication;
import java.util.Properties;

public class JavaMailSenderExample {

    public static void main(String[] args) {
        // Sender's email and password

        final String senderEmail = "sholaibrahimoh.com";
        final String senderPassword = "wdxtkthpxdqjubjy";
//
//        char[] senderPasswordChar = senderPassword.toCharArray();


        // Recipient's email
        final String recipientEmail = "samuelshola14@gmail.com";

        // Set up JavaMail properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.example.com");  // Replace with your SMTP server host
        properties.put("mail.smtp.port", "587");  // Replace with your SMTP server port

        // Create a Session instance with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        try {
            // Create a MimeMessage
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Subject of Your Email");
            message.setText("Hello, this is the content of your email.");

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
