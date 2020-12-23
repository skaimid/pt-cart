package tech.saltyfish.ptcart.controller;

import org.springframework.web.bind.annotation.*;
import tech.saltyfish.ptcart.model.entity.Channel;
import tech.saltyfish.ptcart.service.ChannelRepository;
import tech.saltyfish.ptcart.service.UserRepository;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ChannelController {
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    public ChannelController(ChannelRepository channelRepository, UserRepository userRepository) {
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/channels")
    List<Channel> getChannelByUsername(Principal principal) {
        String username = principal.getName();
        return channelRepository.findByOwnedUser_Username(username);
    }

    @DeleteMapping("/channels/{cid}")
    List<Channel> deleteChannelById(Principal principal, @PathVariable long cid) {
        String username = principal.getName();
        if (channelRepository.findByOwnedUser_Username(username).stream().anyMatch(channel -> channel.getChannelId() == cid)) {
            channelRepository.deleteById(cid);
            return channelRepository.findByOwnedUser_Username(username);
        } else {
            //TODO 设置异常拦截器
            return null;
        }
    }

    @PostMapping("/channels")
    List<Channel> addChannel(Principal principal, @RequestBody Channel channel) {
        channel.setOwnedUser(userRepository.findByUsername(principal.getName()));
        channel.setLinkSet(new LinkedList<>());
        channelRepository.save(channel);
        return channelRepository.findByOwnedUser_Username(principal.getName());
    }


}
