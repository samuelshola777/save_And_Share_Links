package com.example.emailService.controller;

import com.example.emailService.data.model.FriendsConnection;
import com.example.emailService.data.model.User;
import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.request.UserRequest;
import com.example.emailService.dtos.response.LinkResponse;
import com.example.emailService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class
UserController {
    private final UserService userService;
@PostMapping("/register")
   public ResponseEntity<HttpResponse> createUser(@RequestBody UserRequest user) {
    User user1 = userService.saverUser(user);
    return ResponseEntity.created(
            URI.create("")).body(
            HttpResponse.builder()
            .timeStamp(LocalDateTime.now().toString())
                    .data(Map.of("user",user1))
                    .massage("user created successfully")
                    .status(HttpStatus.CREATED)
                    .statusCode(HttpStatus.CREATED.value())
                    .build()
    );
   }

   @GetMapping("/verify")
    public ResponseEntity<HttpResponse> verifyUserAccount(@RequestParam("token") String token){
Boolean isSuccess = userService.verifyToken(token);
return ResponseEntity.ok().body(
        HttpResponse.builder()
                .timeStamp(LocalDateTime.now().toString())
                .data(Map.of("Verification successful", isSuccess))
                .massage("Account verified")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build()
);

   }

    @GetMapping("/accept")
    public ResponseEntity<HttpResponse> acceptFriendRequest(@RequestParam String friendUserName,@RequestParam String friendRequestSenderUserName){
    userService.acceptFriendRequest(friendUserName, friendRequestSenderUserName);
    return ResponseEntity.ok().body(
            HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
                    .data(Map.of("FriendRequest Accept successfully","Accepted"))
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
    );
    }
    @PostMapping("/savelink")
    public ResponseEntity<LinkResponse> saveLink(@RequestBody LinkRequest request){
    return new ResponseEntity<>(userService.saveUrlLink(request),HttpStatus.CREATED);
    }

    @GetMapping("/countuserlink")
    public ResponseEntity<Long> countUserLink(@RequestParam String userEMail){
    return new ResponseEntity<>(userService.countMyLinks(userEMail), HttpStatus.OK);
}


}
