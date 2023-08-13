package com.example.emailService.dtos.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserRequest {
    private String userName;

    private String email;
    private String password;
}
