package com.example.emailService.Exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmailException extends RuntimeException{

    private String message;


}
