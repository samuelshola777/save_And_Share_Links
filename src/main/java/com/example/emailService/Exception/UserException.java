package com.example.emailService.Exception;

import lombok.AllArgsConstructor;


public class UserException extends RuntimeException{
private String message;
public UserException(String message){
    super(message);
}
}
