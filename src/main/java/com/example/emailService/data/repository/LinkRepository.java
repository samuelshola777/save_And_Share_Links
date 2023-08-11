package com.example.emailService.data.repository;

import com.example.emailService.data.model.Links;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Links,Long> {
    boolean ifExistByLinkName(String linkName);
}
