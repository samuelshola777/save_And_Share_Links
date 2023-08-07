package com.example.emailService.Exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserException extends RuntimeException{
private String message;
}
