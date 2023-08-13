package com.example.emailService.services;

import com.example.emailService.dtos.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RequiredArgsConstructor
class UserServiceTest {
    private final UserService userService;


private UserRequest userRequest1;
private UserRequest userRequest2;
private UserRequest userRequest3;

    @BeforeEach
    void setUp() {
        userRequest1 = new UserRequest();
        userRequest1.setUserName("samuel segun");
        userRequest1.setPassword("samuel segun");
        userRequest1.setEmail("samuelsegun@gmail.com");

        userRequest2 = new UserRequest();
        userRequest2.setUserName("samuel shola");
        userRequest2.setEmail("sholaibrahimoh@gmail.co");
        userRequest2.setPassword("blueboat");

        userRequest3 = new UserRequest();
        userRequest3.setUserName("favor mnbata");
        userRequest3.setPassword("SAMUELSHOLA14@GMAIL.COM");
        userRequest3.setEmail("i love java");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saverUser() {
        assertDoesNotThrow(()-> {
            userService.saverUser(userRequest1);
            userService.saverUser(userRequest2);
            userService.saverUser(userRequest3);
        });
    }
}