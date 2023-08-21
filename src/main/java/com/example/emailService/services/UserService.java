package com.example.emailService.services;

import com.example.emailService.data.model.FriendsConnection;
import com.example.emailService.data.model.Links;
import com.example.emailService.data.model.ShareHistory;
import com.example.emailService.data.model.User;
import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.request.UserRequest;
import com.example.emailService.dtos.response.LinkResponse;
import com.example.emailService.dtos.response.UserResponse;
import jakarta.mail.MessagingException;

public interface UserService {
    User saverUser(UserRequest user);
    Boolean verifyToken(String token);

   LinkResponse saveUrlLink(LinkRequest linkRequest1);

    long countMyLinks(String mail);

    String renameUrlLink(String mail, String oldLinkName, String newLinkName);

    FriendsConnection findFriends(String friendName, String friendRequestSender);
    LinkResponse userViewLink(String brevoSiteLink,String userName);

    void deleteLink(String mail, String brevoSiteLink);

    UserResponse userLogin(String mail, String password);

    FriendsConnection userAddFriend(String userEmail, String friendUserName) throws MessagingException;

    void deleteUserByEmail(String iLoveJava);


    FriendsConnection acceptFriendRequest(String friendUserName, String friendRequestUserName);

    ShareHistory sendLinkToFriend(String friendUserName, String linkSenderEmail, String linkLable);
}
