package com.example.emailService.data.repository;

import com.example.emailService.data.model.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationRepository extends JpaRepository<Confirmation,Long> {
    Confirmation findByToken(String token);

}
