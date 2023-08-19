package com.example.emailService.dtos.request;

import com.example.emailService.data.model.User;
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
    private User user;
    private String userName;
}
