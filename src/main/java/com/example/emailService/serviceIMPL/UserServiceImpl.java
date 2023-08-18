package com.example.emailService.serviceIMPL;

import com.example.emailService.EveryThingEmail.EmailRequests.FriendRequestMailRequest;
import com.example.emailService.EveryThingEmail.EmailServices.FriendRequestMailSenderService;
import com.example.emailService.Exception.FriendsConnectionException;
import com.example.emailService.Exception.LinkException;
import com.example.emailService.Exception.UserException;
import com.example.emailService.data.model.*;
import com.example.emailService.data.repository.ConfirmationRepository;
import com.example.emailService.data.repository.FriendsConnectionRepository;
import com.example.emailService.data.repository.ShareHistoryRepository;
import com.example.emailService.data.repository.UserRepository;
import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.request.UserRequest;
import com.example.emailService.dtos.response.LinkResponse;
import com.example.emailService.dtos.response.UserResponse;
import com.example.emailService.services.LinkService;
import com.example.emailService.services.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    private final LinkService linkService;
    private final FriendsConnectionRepository friendsRepository;
    private final FriendRequestMailSenderService friendRequestMail;
    private final ShareHistoryRepository shareHistoryRepository;
    @Override
    public User saverUser(UserRequest user) {
        if(userRepository.existsByEmail(user.getEmail())) throw new UserException(("email address already in use"));
     User savedUser =userRepository.save(mapToUser(user));
      Confirmation  savedConfirmation =  confirmationRepository.save(new Confirmation(savedUser));
        // TODO send email notification with token

        return savedUser;
    }


    @Override
    public Boolean verifyToken(String token) {
        User user;
        try {
            Confirmation confirmation = confirmationRepository.findByToken(token);
        user = userRepository.findByEmailIgnoreCase(confirmation.getUser().getEmail());
        }catch (Exception e){
         log.info(e.getMessage());
         throw new UserException(("invalid token"));
        }
      user.setEnabled(true);
      userRepository.save(user);
        return Boolean.TRUE;
    }
    @Override
    public LinkResponse saveUrlLink(LinkRequest linkRequest1) {
        linkRequest1.setUser(userRepository.findByEmailIgnoreCase(linkRequest1.getUserEmail()));
        return linkService.createLink(linkRequest1);
    }

    @Override
    public long countMyLinks(String mail) {
        return linkService.countMyLinks(mail);
    }
    private Links validateUserLink(String userEmail,String linkUrlName){
        Links foundLinks = linkService.findLinkByLabel(linkUrlName);
        if (!foundLinks.getUserEmail().equals(userEmail)) throw new UserException("invalid email address");
       return foundLinks;
    }

    @Override
    public String renameUrlLink(String mail, String oldLinkName, String newLinkName) {
   validateUserLink(mail, oldLinkName);
    return linkService.renameLink(oldLinkName, newLinkName);
    }

    @Override
    public FriendsConnection findFriends(String friendName, String friendRequestSender) {
        return friendsRepository.findByFriendNameAndFriendRequestSender( friendName,  friendRequestSender);
    }

    @Override
    public Links userViewLink(String email, String linkName) {
        return validateUserLink(email, linkName);
    }

    @Override
    public void deleteLink(String mail, String brevoSiteLink) {
        linkService.deleteLindByLabel(brevoSiteLink,mail);
    }

    @Override
    public UserResponse userLogin(String mail, String password) {
    User foundUser = findByEmail(mail);
    if (! foundUser.getPassword().equalsIgnoreCase(password)) throw new UserException("Invalid password");
    foundUser.setLoggedIn(true);
        return mapToUserResponse(userRepository.save(foundUser));
    }


    @Override
    public FriendsConnection userAddFriend(String userEmail, String friendUserName) throws MessagingException {
        User foundUser = findByEmail(userEmail);
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
        return friendConnection; //
    }


    @Override
    public void deleteUserByEmail(String email) {
      userRepository.delete(findByEmail(email));
    }

    @Override
    public FriendsConnection acceptFriendRequest(String friendUserName, String friendRequestUserName) {
        FriendsConnection foundFriendConnection = findFriends(friendUserName, friendRequestUserName);
        if (foundFriendConnection == null) throw new FriendsConnectionException("No friend request sent to  " + friendUserName);
        foundFriendConnection.setNowFriends(true);
       return   friendsRepository.save(foundFriendConnection);
    }

    @Override
    public ShareHistory sendLinkToFriend(String friendUserName, String linkSenderUserName, String linkLable) {
    if (! findFriends(friendUserName,linkSenderUserName).isNowFriends()) throw new LinkException(friendUserName+"  has not accepted your friend request");
    Links foundLink = linkService.findLink(friendUserName,linkLable);
   Links savedLink =  linkService.saveLink( Links.builder()
                       .createdTime(LocalDateTime.now())
                       .linkName(foundLink.getLinkName())
                       .user(findByUserName(friendUserName))
                       .linkUrlAddress(foundLink.getLinkUrlAddress())
                       .build());
   return shareHistoryRepository.save(ShareHistory.builder()
           .sendTime(LocalDateTime.now())
           .receiverUserName(friendUserName)
           .senderUserName(linkSenderUserName)
           .sharedTime(LocalDateTime.now())
           .build());
    }


    private User findByUserName(String friendUserName) {
        User user = userRepository.findUserByUserName(friendUserName);
        if (user == null) throw new UserException("User with the user name " + friendUserName + "  not found");
        return user;
    }

    private User findByEmail(String mail) {
        User foundUser = userRepository.findByEmailIgnoreCase(mail);
        if (foundUser == null) throw new UserException("Could not find user with email " + mail);
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
