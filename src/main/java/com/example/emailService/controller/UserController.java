package com.example.emailService.controller;

import com.example.emailService.data.model.User;
import com.example.emailService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import static jakarta.mail.event.FolderEvent.CREATED;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
@PostMapping("/register")
   public ResponseEntity<HttpResponse> createUser(@RequestBody User user) {
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





}