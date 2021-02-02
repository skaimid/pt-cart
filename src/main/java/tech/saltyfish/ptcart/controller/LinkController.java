package tech.saltyfish.ptcart.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.saltyfish.ptcart.model.entity.ChannelEntity;
import tech.saltyfish.ptcart.service.LinkService;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LinkController {
    private final LinkService linkServiceImpl;

    public LinkController(LinkService linkServiceImpl) {
        this.linkServiceImpl = linkServiceImpl;
    }


    @PostMapping("/links")
    List<ChannelEntity> addLink(Principal principal, @RequestBody Map<String, String> newLink) {
        String username = principal.getName();
        return linkServiceImpl.addLink(username, Long.parseLong(newLink.get("channelId")), newLink.get("link"));
    }
}
