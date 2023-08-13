package com.example.emailService.services;

import com.example.emailService.data.model.User;
import com.example.emailService.dtos.request.UserRequest;

public interface UserService {
    User saverUser(UserRequest user);
    Boolean verifyToken(String token);

}
