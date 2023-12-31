package com.example.emailService.dtos.response;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Builder
public class LinkResponse {
    private String linkName;
    private long numberOfViews;
    private String userName;
    
}
