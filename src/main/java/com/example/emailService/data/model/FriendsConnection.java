package com.example.emailService.data.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendsConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String friendName;
    private String friendRequestSender;
    private String friendEmailAddress;
    private boolean nowFriends;
    @ManyToOne
    private User user;

}
