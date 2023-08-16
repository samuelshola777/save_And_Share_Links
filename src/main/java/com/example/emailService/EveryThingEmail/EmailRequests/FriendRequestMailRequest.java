package com.example.emailService.EveryThingEmail.EmailRequests;

import lombok.Data;
import org.apache.catalina.Host;

@Data
public class FriendRequestMailRequest {
    private Host host;
    private String friendUsername;
    private String requestSender;
}
