package com.example.emailService.serviceIMPL;

import com.example.emailService.Exception.UserException;
import com.example.emailService.data.model.Confirmation;
import com.example.emailService.data.model.User;
import com.example.emailService.data.repository.ConfirmationRepository;
import com.example.emailService.data.repository.UserRepository;
import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.request.UserRequest;
import com.example.emailService.dtos.response.LinkResponse;
import com.example.emailService.services.EmailService;
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
    private final EmailService emailService;
    private final LinkService linkService;
    @Override
    public User saverUser(UserRequest user) {
        if(userRepository.existsByEmail(user.getEmail())) throw new UserException(("email address already in use"));

     User savedUser =userRepository.save(mapToUser(user));
      Confirmation  savedConfirmation =  confirmationRepository.save(new Confirmation(savedUser));
        // TODO send email notification with token
        emailService.sendSimpleMailMessage("boneshaker",user.getEmail(),savedConfirmation.getToken());
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
    public LinkResponse saverUrlLink(LinkRequest linkRequest1) {
        linkRequest1.setUserId(userRepository.findByEmailIgnoreCase(linkRequest1.getUserEmail()).getId());
        return linkService.createLink(linkRequest1);
    }

    @Override
    public long countMyLinks(String mail) {
        return linkService.countMyLinks(mail);
    }

    private User mapToUser(UserRequest userRequest){
        return User.builder()
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .isEnabled(false)
                .build();
    }
}
