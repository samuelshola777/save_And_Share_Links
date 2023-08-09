package com.example.emailService.dtos.request;

import com.example.emailService.data.model.BrevoEmailReceiver;
import com.example.emailService.data.model.BrevoSender;
import lombok.Data;

import java.util.List;

@Data
public class BrevoEmaiRequest {

    private BrevoSender sender;


    private List<BrevoEmailReceiver> recipients;

    private String textContent;

    private String subject;
}
