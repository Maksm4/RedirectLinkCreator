package org.tpo10.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.tpo10.Models.LinkDTO;
import org.tpo10.Service.LinkService;
import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class LinkAPIController {

    private final LinkService linkService;
    private final ObjectMapper mapper;

    public LinkAPIController(LinkService linkService, ObjectMapper mapper) {
        this.linkService = linkService;
        this.mapper = mapper;
    }

    @Tag(name = "POST", description = "add new link to database")
    @PostMapping(path = "/api/links")
    public ResponseEntity<LinkDTO> saveLink(@RequestBody LinkDTO linkDTO){
        LinkDTO savedLink = linkService.saveLink(linkDTO);
        return ResponseEntity.status(201).location(URI.create("http://localhost:8080/api/links/" + savedLink.getId())).body(savedLink);
    }

    @Tag(name = "GET", description = "get an information about links from database")
    @GetMapping(path = "/api/links/{id}")
    public ResponseEntity<LinkDTO> getLink(@PathVariable String id){
        return linkService.getLinkByID(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Tag(name = "GET", description = "get an information about links from database")
    @GetMapping(path = "/red/{id}")
    public ResponseEntity<RedirectView> redirectToLink(@PathVariable String id){
        Optional<LinkDTO> optionalDto = linkService.getLinkByID(id);
        if (optionalDto.isPresent()){
            LinkDTO linkDTO = optionalDto.get();
            linkDTO.setVisits(linkDTO.getVisits() + 1);
            linkService.saveLink(linkDTO);
            String targetURI = linkDTO.getTargetUrl();
            return ResponseEntity.status(302)
                    .location(URI.create(targetURI))
                    .build();
        }
        return ResponseEntity.notFound().build();
    }

    @Tag(name = "PATCH", description = "update link information in database")
    @PatchMapping(path = "/api/links/{id}")
    public ResponseEntity<?> updateLink(@PathVariable String id, @RequestBody JsonMergePatch patch){
        try {
            LinkDTO linkDTO = linkService.getLinkByID(id).orElseThrow();
            String providedPass = getPassFromPatch(patch);

            if (!providedPass.equals(linkDTO.getPassword()) || linkDTO.getPassword() == null){
                return ResponseEntity.status(403).header("reason", "wrong password").build();
            }

            LinkDTO updatedLink = makePatch(linkDTO, patch);
            linkService.updateLink(updatedLink);

        }catch (NoSuchElementException | JsonProcessingException | JsonPatchException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    private LinkDTO makePatch(LinkDTO linkDTO, JsonMergePatch patch) throws JsonProcessingException, JsonPatchException {
        JsonNode linkNode = mapper.valueToTree(linkDTO);
        JsonNode patchNode = patch.apply(linkNode);
        return mapper.treeToValue(patchNode, LinkDTO.class);
    }

    private String getPassFromPatch(JsonMergePatch patch) throws JsonPatchException {
        JsonNode patchNode = patch.apply(mapper.createObjectNode());
        JsonNode password = patchNode.get("password");
        if (password == null){
            return "";
        }
        return password.asText();
    }

    @Tag(name = "DELETE", description = "Remove link from database")
    @DeleteMapping("/api/links/{id}")
    public ResponseEntity<?> deleteLink(@PathVariable String id, @RequestParam(required = false) String pass){
        Optional<LinkDTO> linkOpt = linkService.getLinkByID(id);
        if (linkOpt.isPresent()){
            LinkDTO linkDTO = linkOpt.get();
            if (pass == null || !pass.equals(linkDTO.getPassword())){
                return ResponseEntity.status(403).header("reason", "wrong password").build();
            }

            linkService.deleteLink(id);
        }
        return ResponseEntity.status(204).build();
    }

}
