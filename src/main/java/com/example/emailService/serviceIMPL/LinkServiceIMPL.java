package com.example.emailService.serviceIMPL;

import com.example.emailService.Exception.LinkException;
import com.example.emailService.data.model.Links;
import com.example.emailService.data.repository.LinkRepository;
import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.response.LinkResponse;
import com.example.emailService.services.LinkService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class LinkServiceIMPL implements LinkService {
    private final LinkRepository linkRepository;
    @Override
    public LinkResponse createLink(LinkRequest linkRequest) {
       if ( linkRepository.ifExistByLinkName(linkRequest.getLinkName()))
           throw new LinkException("link Already exist");
       if (linkRequest.getUserEmail() == null || linkRequest.getLinkUrlAddress() == null)
           throw new LinkException("this operation can't be completed ");
        return mapToResponse(linkRepository.save(mapLinkRequestToLink(linkRequest)));
    }
    public Links mapLinkRequestToLink(LinkRequest linkRequest){
   return  Links.builder()
           .linkUrlAddress(linkRequest.getLinkUrlAddress())
           .linkName(linkRequest.getLinkName())
           .userEmail(linkRequest.getUserEmail())
           .id(linkRequest.getUserId())
           .build();
    }
    public LinkResponse mapToResponse(Links request) {
        return LinkResponse.builder()
                .linkName(request.getLinkName())
                .numberOfLink(request.getNumberOflinks())
                .build();
    }
}
