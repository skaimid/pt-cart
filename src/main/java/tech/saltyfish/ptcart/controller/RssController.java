package tech.saltyfish.ptcart.controller;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Enclosure;
import com.rometools.rome.feed.rss.Item;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.saltyfish.ptcart.model.entity.ChannelEntity;
import tech.saltyfish.ptcart.service.ChannelEntityRepository;
import tech.saltyfish.ptcart.service.UserRepository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;

@RestController
public class RssController {
    private final UserRepository userRepository;
    private final ChannelEntityRepository channelEntityRepository;

    public RssController(UserRepository userRepository, ChannelEntityRepository channelEntityRepository) {
        this.userRepository = userRepository;
        this.channelEntityRepository = channelEntityRepository;
    }

    @RequestMapping("/rss")
    Channel rss(@RequestParam("username") Optional<String> username,
                @RequestParam("token") Optional<String> token,
                @RequestParam("channel") Optional<Long> channelId) {
        Channel channel = new Channel();
        channel.setFeedType("rss_2.0");
        channel.setTitle("HowToDoInJava Feed");
        channel.setDescription("Different Articles on latest technology");
        channel.setLink("https://howtodoinjava.com");
        channel.setUri("https://howtodoinjava.com");
        channel.setGenerator("In House Programming");
        LinkedList<Item> itemLinkedList = new LinkedList<>();
        if (username.isPresent() && token.isPresent() && channelId.isPresent()) {
            if (!username.get().equals("")) {
//                userRepository.findByUsername(username.get());
                ChannelEntity entity = channelEntityRepository.findByChannelIdAndOwnedUser_Username(channelId.get(), username.get());
                entity.getLinkSet().forEach(link -> {
                    Item item = new Item();
                    Enclosure enclosure = new Enclosure();
                    enclosure.setUrl(link.getUrl());
                    enclosure.setType("application/x-bittorrent");
                    item.setEnclosures(Collections.singletonList(enclosure));
                    itemLinkedList.add(item);
                });
                channel.setItems(itemLinkedList);
                return channel;
            }
        }
        return channel;
    }
}
