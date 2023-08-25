package com.example.emailService.EveryThingEmail.EmailServiceIMPs;

import com.example.emailService.EveryThingEmail.EmailRequests.FriendRequestMailRequest;
import com.example.emailService.EveryThingEmail.EmailServices.FriendRequestMailSenderService;
import jakarta.mail.MessagingException;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class FriendRequestMailSender implements FriendRequestMailSenderService {

    private final JavaMailSender javaMailSender;

    @Override
    public String friendRequestMailSender(FriendRequestMailRequest request) throws MessagingException {
        LocalTime.parse("18:00");
        MimeMessage message = javaMailSender.createMimeMessage ();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo (request.getTo ());
        helper.setSubject (request.getSubject());
        helper.setFrom (request.getFrom ());
        helper.setText (request.getMailMessage(), true);
        javaMailSender.send(message);
        return "sent successfully";
    }


}
