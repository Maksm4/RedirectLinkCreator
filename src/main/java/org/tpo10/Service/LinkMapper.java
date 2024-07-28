package org.tpo10.Service;

import org.springframework.stereotype.Service;
import org.tpo10.Models.Link;
import org.tpo10.Models.LinkDTO;

@Service
public class LinkMapper {

    public LinkDTO mapToDto(Link link){
        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setId(link.getId());
        linkDTO.setName(link.getName());
        linkDTO.setTargetUrl(link.getTargetUrl());
        linkDTO.setRedirectUrl(link.getTargetUrl());
        linkDTO.setPassword(link.getPassword());
        linkDTO.setVisits(link.getVisits());
        return linkDTO;
    }

    public Link mapToLink(LinkDTO linkDTO){
        Link link = new Link();
        link.setId(linkDTO.getId());
        link.setName(linkDTO.getName());
        link.setTargetUrl(linkDTO.getTargetUrl());
        link.setPassword(linkDTO.getPassword());
        link.setVisits(linkDTO.getVisits());
        if (linkDTO.getRedirectUrl() == null){
            linkDTO.setRedirectUrl(linkDTO.getTargetUrl());
        }
        link.setRedirectUrl(linkDTO.getRedirectUrl());
        return link;
    }
}
