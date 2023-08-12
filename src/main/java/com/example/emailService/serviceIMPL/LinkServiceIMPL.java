package com.example.emailService.serviceIMPL;

import com.example.emailService.Exception.FIndException;
import com.example.emailService.Exception.LinkException;
import com.example.emailService.data.model.Links;
import com.example.emailService.data.repository.LinkRepository;
import com.example.emailService.dtos.request.LinkRequest;
import com.example.emailService.dtos.response.LinkResponse;
import com.example.emailService.services.LinkService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class LinkServiceIMPL implements LinkService {

    private final LinkRepository linkRepository;
    @Override
    public LinkResponse createLink(LinkRequest linkRequest) {
       if ( linkRepository.existsByLinkName(linkRequest.getLinkName()))
           throw new LinkException("link Already exist");
       if (linkRequest.getUserEmail() == null || linkRequest.getLinkUrlAddress() == null)
           throw new LinkException("this operation can't be completed ");
        return mapToResponse(linkRepository.save(mapLinkRequestToLink(linkRequest)));
    }

    @Override
    public String renameLink(String oldLinkName, String newLinkName) {
        if (! linkRepository.existsByLinkName(oldLinkName)) throw new FIndException("Link " + oldLinkName + " already exists");
        Links foundLink =  linkRepository.findByLinkName(oldLinkName);
        foundLink.setLinkName(newLinkName);
        foundLink.setLastupdatedTime(LocalDateTime.now());
        linkRepository.save(foundLink);
        return "new name set successfully";
    }

    @Override
    public String deleteAllLinkByUserEmail(String mail) {
        linkRepository.deleteAllByUserEmail(mail);
        return "all links deleted successfully";
    }


    public Links mapLinkRequestToLink(LinkRequest linkRequest){
   return  Links.builder()
           .linkUrlAddress(linkRequest.getLinkUrlAddress())
           .linkName(linkRequest.getLinkName())
           .userEmail(linkRequest.getUserEmail())
           .id(linkRequest.getUserId())
           .createdTime(LocalDateTime.now())
           .numberOfLinks(+1)
           .build();
    }
    public LinkResponse mapToResponse(Links request) {
        return LinkResponse.builder()
                .linkName(request.getLinkName())
                .numberOfLink(request.getNumberOfLinks())
                .build();
    }

}
