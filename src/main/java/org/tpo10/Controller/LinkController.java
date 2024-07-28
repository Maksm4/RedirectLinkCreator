package org.tpo10.Controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tpo10.Models.Link;
import org.tpo10.Models.LinkDTO;
import org.tpo10.Service.LinkService;

import java.util.Optional;

@Controller
public class LinkController {
    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/link")
    public String getAddPage(Model model){
        model.addAttribute("link", new LinkDTO());
        return "addLink";
    }

    @PostMapping("/register")
    public String addLink(@Valid @ModelAttribute("link") LinkDTO linkDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "addLink";
        }
        linkService.saveLink(linkDTO);
        return "redirect:success";
    }

    @GetMapping("/success")
    public String showAddedLink(){
        return "success";
    }


    @GetMapping("/checkLink")
    public String getLinkInfo(Model model){
        model.addAttribute("link", new LinkDTO());
       return "showLink";
    }

    @PostMapping("/checkLink")
    public String checkLinkInfo(@Valid @ModelAttribute("link") LinkDTO linkDTO, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "notFound";
        }
        Optional<LinkDTO> optionalLinkDTO = linkService.getLinkByNameAndPassword(linkDTO.getName(),linkDTO.getPassword());
        if (optionalLinkDTO.isPresent()){
            model.addAttribute("foundLink", optionalLinkDTO.get());
            return "found";
        }
        return "notFound";
    }


    @GetMapping("/editLink")
    public String showEditLink(@RequestParam("id") String id, Model model){
        Optional<LinkDTO> optionalLinkDTO = linkService.getLinkByID(id);
        if (optionalLinkDTO.isPresent()){
            LinkDTO linkDTO = optionalLinkDTO.get();
            model.addAttribute("link", linkDTO);
            return "editLink";
        }
        return "notFound";
    }

    @PostMapping("/editLink")
    public String editLink(@Valid @ModelAttribute("link") LinkDTO linkDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "editLink";
        }
        linkService.updateLink(linkDTO);
        return "redirect:success";
    }

    @GetMapping("/deleteLink")
    public String deleteLink(@RequestParam("id") String id){
        linkService.deleteLink(id);
        return "redirect:success";
    }

}
