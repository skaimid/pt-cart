package tech.saltyfish.ptcart.controller;

import org.springframework.web.bind.annotation.*;
import tech.saltyfish.ptcart.model.entity.ChannelEntity;
import tech.saltyfish.ptcart.service.ChannelService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ChannelController {
    private final ChannelService channelServiceImpl;

    public ChannelController(ChannelService channelServiceImpl) {
        this.channelServiceImpl = channelServiceImpl;
    }


    @GetMapping("/channels")
    List<ChannelEntity> getChannelByUsername(Principal principal) {
        return channelServiceImpl.getChannelByUsername(principal.getName());
    }

    @DeleteMapping("/channels/{cid}")
    List<ChannelEntity> deleteChannelById(Principal principal, @PathVariable long cid) {
        return channelServiceImpl.deleteChannelById(principal.getName(), cid);
    }

    @PostMapping("/channels")
    List<ChannelEntity> addChannel(Principal principal, @RequestBody ChannelEntity channelEntity) {
        return channelServiceImpl.addChannel(principal.getName(), channelEntity);
    }


}
