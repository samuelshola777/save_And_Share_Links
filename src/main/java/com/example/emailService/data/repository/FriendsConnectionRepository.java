package com.example.emailService.data.repository;

import com.example.emailService.data.model.FriendsConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendsConnectionRepository extends JpaRepository<FriendsConnection, Long> {

    FriendsConnection findByFriendNameAndFriendRequestSender(String friendName, String friendRequestSender);
}
