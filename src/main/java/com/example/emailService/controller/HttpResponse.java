package com.example.emailService.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.Map;
@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class HttpResponse {
    protected String timeStamp;
    protected int statusCode;
    protected HttpStatus status;
    protected String massage;
    protected String developerMessage;
    protected String path;
    protected String requestMethod;
    protected Map<?,?> data;
    private String token;

}
