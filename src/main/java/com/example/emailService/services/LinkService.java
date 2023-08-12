package com.example.emailService.services;

import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.response.LinkResponse;

public interface LinkService {

    LinkResponse createLink(LinkRequest linkRequest);

   String renameLink(String oldLinkName, String newLinkName);


    String deleteAllLinkByUserEmail(String mail);

    String deleteLindByLabel(String myGoogleLink, String mail);
    long countMyLinks(String userEmail);
}
