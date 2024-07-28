package org.tpo10.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.tpo10.Models.Link;
import org.tpo10.Models.LinkDTO;
import org.tpo10.Repository.LinkRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private final LinkMapper linkMapper;

    public LinkService(LinkRepository linkRepository, LinkMapper linkMapper) {
        this.linkRepository = linkRepository;
        this.linkMapper = linkMapper;
    }

    public LinkDTO saveLink(LinkDTO linkDTO){
        Iterable<Link> links = linkRepository.findAll();
        //create id and regenerate if nedded(already in database)
        boolean canLeave = false;
        if (linkDTO.getId() == null) {
            while (!canLeave) {
                canLeave = true;
                linkDTO.setId(LinkDTO.generateID());
                for (Link l : links) {
                    if (l.getId().equals(linkDTO.getId())) {
                        canLeave = false;
                        break;
                    }
                }
            }
        }
        try {
            Link link = linkRepository.save(linkMapper.mapToLink(linkDTO));
            return linkMapper.mapToDto(link);
        }catch (ConstraintViolationException cve){
            Set<ConstraintViolation<?>> violations = cve.getConstraintViolations();
            violations.forEach(System.err::println);
            return null;
        }

    }

    public Optional<LinkDTO> getLinkByID(String id){
        return  linkRepository.findById(id).map(linkMapper::mapToDto);
    }


    public boolean updateLink(LinkDTO bookDTO){
        try {
            linkRepository.save(linkMapper.mapToLink(bookDTO));
        }catch (ConstraintViolationException cve) {
            Set<ConstraintViolation<?>> violations = cve.getConstraintViolations();
            violations.forEach(System.err::println);
            return false;
        }
        return true;
    }

    public void deleteLink(String id){
        linkRepository.deleteById(id);
    }

    public Optional<LinkDTO> getLinkByNameAndPassword(String name, String password){
        return  linkRepository.getLinkByNameAndPassword(name,password).map(linkMapper::mapToDto);
    }

}
