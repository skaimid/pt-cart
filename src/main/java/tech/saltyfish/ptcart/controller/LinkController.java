package tech.saltyfish.ptcart.controller;

import org.springframework.web.bind.annotation.*;
import tech.saltyfish.ptcart.model.entity.ChannelEntity;
import tech.saltyfish.ptcart.model.entity.Link;
import tech.saltyfish.ptcart.service.ChannelRepository;
import tech.saltyfish.ptcart.service.LinkRepository;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8091", maxAge = 3600)
public class LinkController {
    private final LinkRepository linkRepository;
    private final ChannelRepository channelRepository;


    public LinkController(LinkRepository linkRepository, ChannelRepository channelRepository) {
        this.linkRepository = linkRepository;
        this.channelRepository = channelRepository;
    }

//    @GetMapping("/links")
//    List<Link> all(){
//        return repo.findAll();
//    }
//
//    @GetMapping("/links/{id}")
//    Link one(@PathVariable Long id) throws LinkNotFoundException {
//        return repo.findById(id).orElseThrow(()->new LinkNotFoundException(id));
//    }

    @PostMapping("/links")
    List<ChannelEntity> addLink(Principal principal, @RequestBody Map<String, String> newLink) {
        String username = principal.getName();
        Link link = new Link();
        link.setUrl(newLink.get("link"));
        Optional<ChannelEntity> oc = channelRepository.findByOwnedUser_Username(username)
                .stream()
                .filter(channel -> channel.getChannelId() == Long.parseLong(newLink.get("channelId")))
                .findFirst();
        oc.ifPresent(link::setOwnedChannel);
        linkRepository.save(link);
        return channelRepository.findByOwnedUser_Username(username);
    }
}
