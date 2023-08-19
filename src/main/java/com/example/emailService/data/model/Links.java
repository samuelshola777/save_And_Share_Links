package com.example.emailService.data.model;

import jakarta.persistence.*;
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
    @ManyToOne()
    private User user;
    private String linkUrlAddress;
    private String linkName;
    private String userEmail;
    private String userName;
    private long numberOfViews;
    private LocalDateTime createdTime;
    private LocalDateTime lastUpdatedTime;


}
