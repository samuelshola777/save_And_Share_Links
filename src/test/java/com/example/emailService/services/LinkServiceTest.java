package com.example.emailService.services;

import com.example.emailService.dtos.request.LinkRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LinkServiceTest {
    @Autowired
    private  LinkService linkService;

    private LinkRequest linkRequest1;
    private LinkRequest linkRequest2;
    private LinkRequest linkRequest3;

    @BeforeEach
    void setUp() {
        linkRequest1 = new LinkRequest();
        linkRequest1.setLinkUrlAddress("http://google.com");
        linkRequest1.setLinkName("my google link");
        linkRequest1.setUserEmail("boneshaker896@gmail.com");

        linkRequest2 = new LinkRequest();
        linkRequest2.setUserEmail("sholaibrahimoh@gmail.com");
        linkRequest2.setLinkName("my github link");
        linkRequest2.setLinkUrlAddress("http://linkedin.com");


        linkRequest3 = new LinkRequest();
        linkRequest3.setLinkName("my gmail api link");
        linkRequest3.setLinkUrlAddress("https://github.com/samuelshola777");
        linkRequest3.setUserEmail("samuelshola14@gmail.com");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Disabled
    void testThatWeCreateLink() {

     assertDoesNotThrow(()->{ linkService.createLink(linkRequest1);});
     assertDoesNotThrow(()->{ linkService.createLink(linkRequest2);});
     assertDoesNotThrow(()->{ linkService.createLink(linkRequest3);});


    }
    @Test
    void testThatWeCanRenameLink(){
        assertDoesNotThrow(()->{linkService.renameLink("my gmail api link","my new gmail ling");});
    }
    @Test
    void testThatWeCanDeleteLink(){
        assertDoesNotThrow(()->{linkService.deleteAllLinkByUserEmail("boneshaker896@gmail.com");});
    }
    @Test
    void testThatWeCanDeleteLinkByLabel() {
        assertDoesNotThrow(()->{linkService.deleteLindByLabel("my google link","boneshaker896@gmail.com");});
    }
    @Test
    void testThatWeCanViewLink(){
       assertEquals("http://google.com",linkService.viewLink("my google link", "boneshaker896@gmail.com").getLinkUrl());
    }
}