package com.example.emailService.services;

import com.example.emailService.data.model.Links;
import com.example.emailService.data.model.User;
import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.request.UserRequest;
import com.example.emailService.dtos.response.LinkResponse;
import com.example.emailService.dtos.response.UserResponse;

public interface UserService {
    User saverUser(UserRequest user);
    Boolean verifyToken(String token);

   LinkResponse saveUrlLink(LinkRequest linkRequest1);

    long countMyLinks(String mail);

    String renameUrlLink(String mail, String oldLinkName, String newLinkName);

    Links userViewLink(String email, String brevoSiteLink);

    void deleteLink(String mail, String brevoSiteLink);

    UserResponse userLogin(String mail, String password);
}
