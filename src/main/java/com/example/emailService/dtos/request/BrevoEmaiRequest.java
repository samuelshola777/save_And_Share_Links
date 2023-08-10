package com.example.emailService.dtos.request;

import com.example.emailService.data.model.BrevoEmailReceiver;
import com.example.emailService.data.model.BrevoSender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BrevoEmaiRequest {

    private BrevoSender sender;

    @JsonProperty("to")
    private List<BrevoEmailReceiver> recipients;

    @JsonProperty("cc")
    private List<String> copiedEmails;

    private String textContent;

    private String subject;
}
