package com.example.emailService.services;

import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class UserServiceTest {
    @Autowired
    private  UserService userService;


private UserRequest userRequest1;
private UserRequest userRequest2;
private UserRequest userRequest3;

    private LinkRequest linkRequest1;
    private LinkRequest linkRequest2;
    private LinkRequest linkRequest3;

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

        linkRequest1 = new LinkRequest();
        linkRequest1.setLinkUrlAddress("http://google.com");
        linkRequest1.setLinkName("my google link");
        linkRequest1.setUserEmail("samuelsegun@gmail.com");

        linkRequest2 = new LinkRequest();
        linkRequest2.setUserEmail("samuelsegun@gmail.com");
        linkRequest2.setLinkName("my github link");
        linkRequest2.setLinkUrlAddress("http://linkedin.com");


        linkRequest3 = new LinkRequest();
        linkRequest3.setLinkName("my gmail api link");
        linkRequest3.setLinkUrlAddress("https://github.com/samuelshola777");
        linkRequest3.setUserEmail("samuelsegun@gmail.com");
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
    @Test
    void testThatWeCanCreateLink(){
        userService.saverUrlLink(linkRequest1);
        userService.saverUrlLink(linkRequest2);
        userService.saverUrlLink(linkRequest3);
        assertEquals(3, userService.countMyLinks("samuelsegun@gmail.com"));

    }
    @Test
    void testThatUserCanRemainLink(){
      assertDoesNotThrow(()->{  userService.renameUrlLink("samuelsegun@gmail.com","my gmail api link","brevo site link");});
    }

}