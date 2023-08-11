package com.example.emailService.services;

import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.response.LinkResponse;

public interface LinkService {

    LinkResponse createLink(LinkRequest linkRequest);
}
