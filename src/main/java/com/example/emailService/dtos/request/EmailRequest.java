package com.example.emailService.dtos.request;

import com.example.emailService.data.model.MailInfo;
import lombok.Data;

import java.util.List;

@Data

public class EmailRequest {
    private MailInfo sender;

    private List<MailInfo> to;

    private String subject;

    private String htmlContent;
}
