package com.example.emailService.EveryThingEmail.EmailServices;

import com.example.emailService.EveryThingEmail.EmailRequests.FriendRequestMailRequest;
import jakarta.mail.MessagingException;

public interface FriendRequestMailSenderService {
    String friendRequestMailSender(FriendRequestMailRequest request) throws MessagingException;
}
