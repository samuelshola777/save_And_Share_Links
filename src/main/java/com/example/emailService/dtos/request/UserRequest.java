package com.example.emailService.dtos.request;

import com.example.emailService.data.model.User;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserRequest {
    private String userName;
    private String email;
    private String password;

}
