package com.example.emailService.services;

import com.example.emailService.data.model.User;

public interface UserService {
    User saverUser(User user);
    Boolean verifyToken(String token);
}
