package com.example.emailService.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FriendsConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String friendName;
    private String friendWith;
    private String friendWithEmailAddress;
    private boolean friendNow;

}
