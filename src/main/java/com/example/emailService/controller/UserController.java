package com.example.emailService.controller;

import com.example.emailService.data.model.FriendsConnection;
import com.example.emailService.data.model.User;
import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.request.UserRequest;
import com.example.emailService.dtos.response.FriendsConnectionResponse;
import com.example.emailService.dtos.response.LinkResponse;
import com.example.emailService.dtos.response.UserResponse;
import com.example.emailService.services.UserService;
import jakarta.mail.MessagingException;
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

    @GetMapping("/count-user-link")
    public ResponseEntity<Long> countUserLink(@RequestParam("user-email") String userEMail){
    return new ResponseEntity<>(userService.countMyLinks(userEMail), HttpStatus.OK);
}
@PutMapping("/rename-link")
    public ResponseEntity<String> renameLink(@RequestParam("user-email")String userEmail, @RequestParam("old-link-name") String oldLinkName,@RequestParam("new-link-name") String newLinkName){
    return ResponseEntity.ok(userService.renameUrlLink(userEmail, oldLinkName, newLinkName));

}
@GetMapping("/view-link")
    public ResponseEntity<LinkResponse> viewLink( @RequestParam("link-name") String linkName, @RequestParam("user-name") String userName){
    return new ResponseEntity<>(userService.userViewLink( linkName, userName), HttpStatus.OK);
}

@DeleteMapping("/delete-link")
    public ResponseEntity<String> deleteLink(@RequestParam("user-name")String userName, @RequestParam("link-name")String linkName){
    userService.deleteLink( linkName ,userName);
    return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
}
@PostMapping("/login")
    public ResponseEntity<UserResponse> userLogin(@RequestParam("user-email") String userEmail, @RequestParam("password") String password){
    return new ResponseEntity<>(userService.userLogin(userEmail,password), HttpStatus.OK);
}
@PutMapping("/add-friend")
    public ResponseEntity<FriendsConnectionResponse> addFriend(@RequestParam("userEmail") String userEmail, @RequestParam("friendUserName") String friendUserName) throws MessagingException {
    return new ResponseEntity<>(userService.userAddFriend(userEmail,friendUserName),HttpStatus.FOUND);
}


}
