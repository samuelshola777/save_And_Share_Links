package com.example.emailService.dtos.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class ShareLinkRequest {
    private String receiverName;
    private String senderName;
    private String receiverEmailAddress;
    private String linkName;
    private String linkAddress;
    private LocalDateTime shareDateTime;
}
