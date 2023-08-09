package com.example.emailService.Exception;

import lombok.AllArgsConstructor;

public class EmailException extends RuntimeException{

    private String message;

   public EmailException(String message){
       super(message);
   }
}
