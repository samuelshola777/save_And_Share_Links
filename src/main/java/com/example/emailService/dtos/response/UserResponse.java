package com.example.emailService.dtos.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private long numberOfLinks;
    private boolean loggedIn;
    private String userName;
    private long numberOfFriends;
    private String token;
}
