package com.example.emailService.serviceIMPL;

import com.example.emailService.Exception.UserException;
import com.example.emailService.data.model.Confirmation;
import com.example.emailService.data.model.User;
import com.example.emailService.data.repository.ConfirmationRepository;
import com.example.emailService.data.repository.UserRepository;
import com.example.emailService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;
    @Override
    public User saverUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) throw new UserException(("email address already in use"));
        user.setEnabled(false);
        confirmationRepository.save(new Confirmation(userRepository.save(user)));        return null;
    }

    @Override
    public Boolean verifyToken(String token) {
        return null;
    }
}
