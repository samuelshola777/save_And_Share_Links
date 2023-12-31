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
       if ( linkRepository.existsByLinkNameAndUserName(linkRequest.getLinkName(), linkRequest.getUser().getUsername()))
           throw new LinkException("link Already exist");
       if (linkRequest.getUserEmail() == null || linkRequest.getLinkUrlAddress() == null)
           throw new LinkException("user Email cannot be empty or link URL address cannot be empty");
       if (linkRequest.getUser() == null) throw new LinkException("invalid user");
       linkRequest.setUserEmail(linkRequest.getUser().getEmail());
       linkRequest.setUserName(linkRequest.getUser().getUsername());
        return mapToResponse(linkRepository.save(mapLinkRequestToLink(linkRequest)));
    }


    @Override
    public String renameLink(String oldLinkName, String newLinkName, String userName) {
        if (! linkRepository.existsByLinkNameAndUserName(oldLinkName, userName)) throw new FIndException("Link " + oldLinkName + " does not exists");
        Links foundLink =  linkRepository.findByLinkNameAndUserName(oldLinkName, userName);
        foundLink.setLinkName(newLinkName);
        foundLink.setLastUpdatedTime(LocalDateTime.now());
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
    public String deleteLindByLabel(String myGoogleLink, String userName) {
       linkRepository.delete(validateLink(myGoogleLink, userName));
        return "deleted successfully";
    }
public Links validateLink(String linkName, String userName){
      Links   foundLinks   = linkRepository.findByLinkNameAndUserName( linkName, userName);
        if (foundLinks == null) throw new LinkException("could not find link  link with name " + linkName);
        return foundLinks;
}

    public Links mapLinkRequestToLink(LinkRequest linkRequest){
   return  Links.builder()
           .linkUrlAddress(linkRequest.getLinkUrlAddress())
           .linkName(linkRequest.getLinkName())
           .userEmail(linkRequest.getUserEmail())
           .createdTime(LocalDateTime.now())
           .userName(linkRequest.getUserName())
           .user(linkRequest.getUser())
           .build();
    }
    public long countMyLinks(String userEmail){
   return linkRepository.countAllByUserEmail(userEmail);
    }

    @Override
    public LinkResponse viewLink(String linkLabel, String userName) {
   Links foundLink = findLinkByLabelAndUserName(linkLabel,userName);
   foundLink.setNumberOfViews(foundLink.getNumberOfViews()+1);
   return mapToResponse(linkRepository.save(foundLink));
    }

    @Override
    public Links findLinkByLabelAndUserName(String linkLabel,String userName) {
    Links links = linkRepository.findByLinkNameAndUserName(  linkLabel , userName);
    if (links == null) throw new LinkException(">> Could not find link by label -> " + linkLabel +"  from this user --> "+userName);
       return links;
    }

//    @Override
//    public Links findLink(String userEmail, String linkLabel) {
//        Links foundLinks = linkRepository.findByUserEmailAndLinkName(userEmail, linkLabel);
//        try {
//            if (foundLinks == null)throw new LinkException("Link URL not found: " + linkLabel);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//        return foundLinks;
//    }

    @Override
    public Links saveLink(Links link) {
        return linkRepository.save(link);
    }

    @Override
    public boolean ifExist(String linkName, String userName) {
   if (linkRepository.existsByLinkNameAndUserName(linkName, userName))  throw new LinkException("link Already exist");;
    return false;
    }


    public LinkResponse mapToResponse(Links request) {
        return LinkResponse.builder()
                .linkName(request.getLinkName())
                .numberOfViews(request.getNumberOfViews())
                .userName(request.getUserName())
                .build();
    }



}
