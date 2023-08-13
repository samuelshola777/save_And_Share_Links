package com.example.emailService.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Links {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long userId;
    private String linkUrlAddress;
    private String linkName;
    private String userEmail;
    private long numberOfViews;
    private long numberOfLinks;
    private LocalDateTime createdTime;
    private LocalDateTime lastupdatedTime;


}
