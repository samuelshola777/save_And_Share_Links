package com.example.emailService.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String senderUserName;
    @Column(nullable = false)
    private String receiverUserName;
    private LocalDateTime sendTime;
    @Column(nullable = true)
    private String linkName;
    private LocalDateTime sharedTime;


}
