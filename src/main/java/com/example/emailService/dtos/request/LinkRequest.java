package com.example.emailService.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkRequest {
    private String linkUrlAddress;
    private String linkName;
    private String userEmail;
    private long userId;
    private long numberOfLinks;
    private String userName;
}
