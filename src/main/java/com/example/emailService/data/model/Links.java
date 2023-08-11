package com.example.emailService.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Links {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    private String userEmail;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;


}
