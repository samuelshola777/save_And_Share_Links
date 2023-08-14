package com.example.emailService.services;

import com.example.emailService.data.model.User;
import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.request.UserRequest;
import com.example.emailService.dtos.response.LinkResponse;

public interface UserService {
    User saverUser(UserRequest user);
    Boolean verifyToken(String token);

   LinkResponse saverUrlLink(LinkRequest linkRequest1);

    long countMyLinks(String mail);
}
