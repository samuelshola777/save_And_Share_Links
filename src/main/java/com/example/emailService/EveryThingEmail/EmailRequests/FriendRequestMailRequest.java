package com.example.emailService.EveryThingEmail.EmailRequests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Host;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class FriendRequestMailRequest {

    private String to;
    private String from;
    private String host = "http://localhost:9000/api/accept";
    private final String subject = " hi   \uD83D\uDC4B  "+to+"     \uD83D\uDC4B    " +
            "\n -->"+from+"  is trying to connect with you to be able to share files and interact" ;
    private final String mailMessage = "\n\nplease on the link to accept   \uD83D\uDC49\uD83D\uDC49\uD83D\uDC49  "+host+"\n" +
            "   \uD83D\uDC69\u200D❤\uFE0F\u200D\uD83D\uDC68\uD83D\uDC69\u200D❤\uFE0F\u200D\uD83D\uDC68\uD83D\uDC69\u200D❤\uFE0F\u200D\uD83D\uDC68";

}
