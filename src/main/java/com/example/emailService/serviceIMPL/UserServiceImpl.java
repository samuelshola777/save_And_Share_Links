package com.example.emailService.serviceIMPL;

import com.example.emailService.EveryThingEmail.EmailServices.FriendRequestMailSenderService;
import com.example.emailService.Exception.FriendsConnectionException;
import com.example.emailService.Exception.LinkException;
import com.example.emailService.Exception.UserException;
import com.example.emailService.data.model.*;
import com.example.emailService.data.repository.FriendsConnectionRepository;
import com.example.emailService.data.repository.ShareHistoryRepository;
import com.example.emailService.data.repository.UserRepository;
import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.request.UserRequest;
import com.example.emailService.dtos.response.FriendsConnectionResponse;
import com.example.emailService.dtos.response.LinkResponse;
import com.example.emailService.dtos.response.UserResponse;
import com.example.emailService.services.LinkService;
import com.example.emailService.services.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LinkService linkService;
    private final FriendsConnectionRepository friendsRepository;
    private final FriendRequestMailSenderService friendRequestMail;
    private final ShareHistoryRepository shareHistoryRepository;
    @Override
    public User saverUser(UserRequest user) {
        if(userRepository.existsByEmail(user.getEmail())) throw new UserException(("email address already in use"));
     return userRepository.save(mapToUser(user));
//      Confirmation  savedConfirmation =  confirmationRepository.save(new Confirmation(savedUser));
        // TODO send email notification with token

//        return savedUser;
    }



    @Override
    public LinkResponse saveUrlLink(LinkRequest linkRequest1) {
        User user = userRepository.findByEmailIgnoreCase(linkRequest1.getUserEmail());
        linkRequest1.setUser(user);
        linkRequest1.setUserName(user.getUserName());
        return linkService.createLink(linkRequest1);
    }

    @Override
    public long countMyLinks(String mail) {
        return linkService.countMyLinks(mail);
    }
    private Links validateUserLink(String linkUrlName,String userName){
        Links foundLinks = linkService.findLinkByLabelAndUserName(linkUrlName, userName);
        if (!foundLinks.getUserEmail().equals(userName)) throw new UserException("invalid email address");
       return foundLinks;
    }

    @Override
    public String renameUrlLink(String mail, String oldLinkName, String newLinkName) {
   Links links = validateUserLink(mail, oldLinkName);
    return linkService.renameLink(oldLinkName, newLinkName,links.getUserName());
    }

    @Override
    public FriendsConnection findFriends(String friendName, String friendRequestSender) {
        return friendsRepository.findByFriendNameAndFriendRequestSender( friendName,  friendRequestSender);
    }

    @Override
    public LinkResponse userViewLink( String linkName,String email) {
        return linkService.viewLink( linkName,email);
    }

    @Override
    public void deleteLink( String brevoSiteLink,String userName) {
        linkService.deleteLindByLabel(brevoSiteLink,userName);
    }

    @Override
    public UserResponse userLogin(String mail, String password) {
    User foundUser = findUserByEmail(mail);
    if (! foundUser.getPassword().equalsIgnoreCase(password)) throw new UserException("Invalid password");
    foundUser.setLoggedIn(true);
        return mapToUserResponse(userRepository.save(foundUser));
    }


    @Override
    public FriendsConnectionResponse userAddFriend(String userEmail, String friendUserName) throws MessagingException {
        User foundUser = findUserByEmail(userEmail);
     if (findFriends(friendUserName, foundUser.getUserName()) != null) throw new FriendsConnectionException("friend connection already exists");
        FriendsConnection   friendConnection =  FriendsConnection.builder()
                .friendName(friendUserName)
                .user(foundUser)
                .friendRequestSender(foundUser.getUserName())
                .friendEmailAddress(findByUserName(friendUserName).getEmail())
                .nowFriends(false)
                .build();
        if (friendConnection.getUser().getId() <= 0) throw new UserException("user most be provided");
        friendsRepository.save(friendConnection);
// todo : send friend request mail notification to friend

//        friendRequestMail.friendRequestMailSender(
//                FriendRequestMailRequest.builder()
//                        .from(foundUser.getEmail())
//                        .to(friendConnection.getFriendEmailAddress())
//                        .build()
//        );
        return mapToFriendConnectionResponse(friendConnection); //
    }


    @Override
    public void deleteUserByEmail(String email) {

        userRepository.delete(findUserByEmail(email));
    }
public FriendsConnectionResponse mapToFriendConnectionResponse(FriendsConnection friendConnection){
        return FriendsConnectionResponse.builder()
                .friendName(friendConnection.getFriendName())
                .friendRequestSenderUserName(friendConnection.getFriendRequestSender())
                .nowFriends(friendConnection.isNowFriends())
                .build();
}
    @Override
    public FriendsConnectionResponse acceptFriendRequest(String friendUserName, String friendRequestUserName) {
        FriendsConnection foundFriendConnection = findFriends(friendUserName, friendRequestUserName);
        if (foundFriendConnection == null) throw new FriendsConnectionException("No friend request sent to  " + friendUserName);
        foundFriendConnection.setNowFriends(true);
       return   mapToFriendConnectionResponse(friendsRepository.save(foundFriendConnection));
    }

    @Override
    public ShareHistory sendLinkToFriend(String friendUserName, String linkSenderUserName, String linkName) {
        String userName =findByUserName(linkSenderUserName).getUserName(); // todo trying to get the user name
        User foundFriend = findByUserName(friendUserName); // todo trying to get the friend account
        FriendsConnection foundConnection = findFriends(friendUserName,userName);
if (foundConnection == null) foundConnection = findFriends(userName,friendUserName);
if (foundConnection == null) throw new FriendsConnectionException("friend connection between user " + friendUserName +" and user " + userName+" does not exist");
if (! foundConnection.isNowFriends() ) throw new LinkException(friendUserName+"  has not accepted your friend request");
    Links foundLink = linkService.findLinkByLabelAndUserName(linkName,userName);
   if (linkService.ifExist(linkName, friendUserName))// todo checking if friend already have the link in his repository
    throw new LinkException("user with the name " + friendUserName+" already have link at hand");


    Links savedLink =  linkService.saveLink( Links.builder()
                        .userEmail(foundFriend.getEmail())
                       .createdTime(LocalDateTime.now())
                       .linkName(foundLink.getLinkName())
                       .userName(foundFriend.getUserName())
                       .user(foundFriend)
                       .linkUrlAddress(foundLink.getLinkUrlAddress())
                       .build());
   return shareHistoryRepository.save(ShareHistory.builder()
           .sendTime(LocalDateTime.now())
           .receiverUserName(friendUserName)
           .linkName(foundLink.getLinkName())
           .senderUserName(userName)
           .sharedTime(LocalDateTime.now())
           .build());
    }

    public static void main(String[] args) {

        for(int row = 1; row <= 10; row++){
            for(int column = 1; column <= row; column++){
                System.out.print("*");
            }
            for(int column = 1; column <= 11 - row; column++){
                System.out.print(" ");
            }
            for(int column = 1; column <= 11 - row; column++){
                System.out.print("*");
            }
            for(int column = 1; column <= row; column++){
                System.out.print(" ");
            }
            for(int column = 1; column <= row; column++){
                System.out.print(" ");
            }
            for(int column = 1; column <= 11 - row; column++){
                System.out.print("*");
            }
            for(int column = 0; column <= 11 - row; column++){
                System.out.print(" ");
            }
            for(int column = 0; column <= row; column++){
                System.out.print("*");
            }
            System.out.println();
        }

    }


    private User findByUserName(String friendUserName) {
        User user = userRepository.findUserByUserName(friendUserName);
        if (user == null) throw new UserException("User with the user name " + friendUserName + "  not found");
        return user;
    }

    private User findUserByEmail(String userName) {
        User foundUser = userRepository.findByEmailIgnoreCase(userName);
        if (foundUser == null) throw new UserException("Could not find user with email " + userName);
        return foundUser;
    }

    private User mapToUser(UserRequest userRequest){
        return User.builder()
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .isEnabled(false)
                .build();
    }
    private UserResponse mapToUserResponse(User user){
    return UserResponse.builder()
            .loggedIn(user.isLoggedIn())
            .numberOfLinks(user.getNumberOfLinks())
            .userName(user.getUserName())
            .build();
    }


}
