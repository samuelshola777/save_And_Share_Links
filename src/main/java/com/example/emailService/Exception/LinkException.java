package com.example.emailService.Exception;

public class LinkException extends RuntimeException{
    private String message;
    public LinkException(String message){
        super(message);
    }
}
