package com.example.emailService.services;

import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.request.UserRequest;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
    private LinkRequest linkRequest4;

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
        userRequest3.setPassword("i love java");
        userRequest3.setEmail("SAMUELSHOLA14@GMAIL.COM");

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

        linkRequest4 = new LinkRequest();
        linkRequest4.setLinkName("java google cloud api page");
        linkRequest4.setLinkUrlAddress("https://developers.google.com/gmail/api/quickstart/java");
        linkRequest4.setUserEmail("SAMUELSHOLA14@GMAIL.COM");
    }

    @AfterEach
    void tearDown() {
    }
@Disabled
    @Test
    void saverUser() {
        assertDoesNotThrow(()-> {
            userService.saverUser(userRequest1);
            userService.saverUser(userRequest2);
            userService.saverUser(userRequest3);
        });
    }
    @Disabled
    @Test
    void testThatWeCanCreateLink(){
        userService.saveUrlLink(linkRequest1);
        userService.saveUrlLink(linkRequest2);
        userService.saveUrlLink(linkRequest3);
        userService.saveUrlLink(linkRequest4);
        assertEquals(3, userService.countMyLinks("samuelsegun@gmail.com"));

    }
    @Test
    void testThatUserCanRemainLink(){
      assertDoesNotThrow(()->{  userService.renameUrlLink("samuelsegun@gmail.com","my gmail api link","brevo site link");});
    }
    @Test
    void testThatUserCanViewLink(){
        assertEquals("samuelsegun",userService.userViewLink("samuel segun","my real git hub link").getLinkName());
    }
    @Test
    void testThatUserCanDeleteLink() {
        assertDoesNotThrow(()-> {
            userService.deleteLink("samuelsegun@gmail.com","brevo site link");
        });
    }
    @Test
    void testThatUserCanLogin(){
        assertEquals(true,  userService.userLogin("samuelsegun@gmail.com","samuel segun").isLoggedIn());
    }
    @Test
    void testThatUserCanAddFriend() throws MessagingException {
        userService.userAddFriend("samuelsegun@gmail.com","favor mnbata");
//    assertEquals(true, );

    }
    @Test
    void testThatWeCanFindFriendsConnectionByNameOfTwoFriends(){
        assertEquals("samuelsegun@gmail.com", userService.findFriends("favor mnbata","samuel segun"));
    }
    @Test
    void testThatWeCanSendFriendRequest(){
       assertTrue( userService.acceptFriendRequest("favor mnbata", "samuel segun").isNowFriends());
    }
    @Test
    void testThatWeCanSendLinkToFriend(){
        assertDoesNotThrow(()-> {
            userService.sendLinkToFriend("favor mnbata", "samuelsegun@gmail.com","my github link");
        });

    }

}