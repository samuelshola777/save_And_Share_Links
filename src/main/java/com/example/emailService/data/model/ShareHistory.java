package com.example.emailService.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDateTime;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ShareHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String senderEmail;
    @Column(nullable = true)
    private String receiverEmail;
    private LocalDateTime sendTime;
    @Column(nullable = true)
    private String linkName;
    private LocalDateTime createdTime;
    @Column(nullable = true)
    private String link;

}
