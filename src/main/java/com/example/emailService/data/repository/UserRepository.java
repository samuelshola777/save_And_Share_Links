package com.example.emailService.data.repository;

import com.example.emailService.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
   User findByEmailIgnoreCase(String email);
   boolean existsByEmail(String email);

    User findUserByUserName(String friendUserName);
}
