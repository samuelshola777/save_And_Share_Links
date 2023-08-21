package com.example.emailService.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendsConnectionResponse {
    private String friendName;
    private String friendRequestSenderUserName;
    private boolean nowFriends;
}
