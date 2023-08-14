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
       if (linkRequest.getUserId() < 1) throw new LinkException("invalid user");
        return mapToResponse(linkRepository.save(mapLinkRequestToLink(linkRequest)));
    }

    @Override
    public String renameLink(String oldLinkName, String newLinkName) {
        if (! linkRepository.existsByLinkName(oldLinkName)) throw new FIndException("Link " + oldLinkName + " does not exists");
        Links foundLink =  linkRepository.findByLinkName(oldLinkName);
        foundLink.setLinkName(newLinkName);
        foundLink.setLastupdatedTime(LocalDateTime.now());
        linkRepository.save(foundLink);
        return "new name set successfully";
    }

    @Override
    public String deleteAllLinkByUserEmail(String mail) {
        try {
            linkRepository.deleteAllByUserEmail(mail);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw new FIndException("invalid link email");
        }

        return "all links deleted successfully";
    }

    @Override
    public String deleteLindByLabel(String myGoogleLink, String mail) {
       linkRepository.delete(validateLink(myGoogleLink, mail));
        return "deleted successfully";
    }
public Links validateLink(String myGoogleLink, String mail){
    if (! linkRepository.existsByLinkName(myGoogleLink)) throw new LinkException("Link " + myGoogleLink + " does not exist");
    Links foundLink =  linkRepository.findByLinkName(myGoogleLink);
    if (! foundLink.getUserEmail().equals(mail)) throw new   LinkException("mail " + mail+ " is not a valid email address");
return foundLink;
}

    public Links mapLinkRequestToLink(LinkRequest linkRequest){
   return  Links.builder()
           .linkUrlAddress(linkRequest.getLinkUrlAddress())
           .linkName(linkRequest.getLinkName())
           .userEmail(linkRequest.getUserEmail())
           .userId(linkRequest.getUserId())
           .createdTime(LocalDateTime.now())
           .build();
    }
    public long countMyLinks(String userEmail){
   return linkRepository.countAllByUserEmail(userEmail);
    }

    @Override
    public LinkResponse viewLink(String linkLabel, String mail) {
   Links foundLink = validateLink(linkLabel, mail);
   foundLink.setNumberOfViews(+1);
   return mapToResponse(linkRepository.save(foundLink));
    }

    @Override
    public Links findLinkByLabel(String linkLabel) {
    Links links = linkRepository.findByLinkName(linkLabel);
    if (links == null) throw new LinkException("Could not find link by label " + linkLabel);
       return links;
    }

    public LinkResponse mapToResponse(Links request) {
        return LinkResponse.builder()
                .linkName(request.getLinkName())
                .numberOfLink(request.getNumberOfViews())
                .build();
    }



}
