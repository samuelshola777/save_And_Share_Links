package com.example.emailService.data.repository;

import com.example.emailService.data.model.Links;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Links,Long> {
    boolean existsByLinkNameAndUserName(String linkName, String userName);

    Links findByLinkNameAndUserName(String oldLinkName, String userName);

    void deleteAllByUserEmail(String mail);


    long countAllByUserEmail(String userEmail);

}
