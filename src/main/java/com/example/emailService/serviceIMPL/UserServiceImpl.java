package com.example.emailService.serviceIMPL;

import com.example.emailService.EveryThingEmail.EmailServices.FriendRequestMailSenderService;
import com.example.emailService.Exception.UserException;
import com.example.emailService.data.model.Confirmation;
import com.example.emailService.data.model.FriendsConnection;
import com.example.emailService.data.model.Links;
import com.example.emailService.data.model.User;
import com.example.emailService.data.repository.ConfirmationRepository;
import com.example.emailService.data.repository.FriendsConnectionRepository;
import com.example.emailService.data.repository.UserRepository;
import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.request.UserRequest;
import com.example.emailService.dtos.response.LinkResponse;
import com.example.emailService.dtos.response.UserResponse;
import com.example.emailService.services.LinkService;
import com.example.emailService.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;

    private final LinkService linkService;
    private final FriendsConnectionRepository friendsRepository;
    private final FriendRequestMailSenderService friendRequestMail;
    @Override
    public User saverUser(UserRequest user) {
        if(userRepository.existsByEmail(user.getEmail())) throw new UserException(("email address already in use"));
     User savedUser =userRepository.save(mapToUser(user));
      Confirmation  savedConfirmation =  confirmationRepository.save(new Confirmation(savedUser));
        // TODO send email notification with token

        return savedUser;
    }
    public void acceptFriendRequest(String userName, String friendName){
        User foundUser = findByUserName(userName);
        FriendsConnection friendsConnection = new FriendsConnection();
        for (int i = 0; i < foundUser.getListOfFriends().size(); i++) {
           if (foundUser.getListOfFriends().get(i).getFriendName().equalsIgnoreCase(friendName)){
               friendsConnection = foundUser.getListOfFriends().get(i);

           }

        }
        friendsConnection.setNowFriends(true);

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
        linkRequest1.setUserId(userRepository.findByEmailIgnoreCase(linkRequest1.getUserEmail()).getId());
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
    public FriendsConnection userAddFriend(String userEmail, String friendUserName) {
        User foundUser = findByEmail(userEmail);
        FriendsConnection   friendConnection =  FriendsConnection.builder()
                .friendName(friendUserName)
                .friendRequestSender(foundUser.getUserName())
                .friendWithEmailAddress(findByUserName(friendUserName).getEmail())
                .nowFriends(false)
                .build();
        foundUser.getListOfFriends().add(friendsRepository.save(friendConnection));
// todo : send friend request mail notification to friend

        return null;
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
