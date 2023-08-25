package com.example.emailService.EveryThingEmail.EmailServices;

import com.example.emailService.EveryThingEmail.EmailRequests.FriendRequestMailRequest;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FriendRequestMailSenderServiceTest {

    @Autowired
    private FriendRequestMailSenderService friendRequestMailSenderService;


    @Test
    void friendRequestMailSender() throws MessagingException {
        FriendRequestMailRequest mailRequest = new FriendRequestMailRequest();

        mailRequest.setTo("samuelshola14@gmail.com");




    friendRequestMailSenderService.friendRequestMailSender(mailRequest);

    }
}