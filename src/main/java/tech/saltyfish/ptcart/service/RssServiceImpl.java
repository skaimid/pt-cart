package tech.saltyfish.ptcart.service;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Enclosure;
import com.rometools.rome.feed.rss.Item;
import org.springframework.stereotype.Service;
import tech.saltyfish.ptcart.model.entity.ChannelEntity;
import tech.saltyfish.ptcart.repository.ChannelEntityRepository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;

@Service
public class RssServiceImpl implements RssService {

    private final ChannelEntityRepository channelEntityRepository;

    public RssServiceImpl(ChannelEntityRepository channelEntityRepository) {
        this.channelEntityRepository = channelEntityRepository;
    }


    @Override
    public Channel getChannelByUsernameAndChannelId(Optional<String> username, Optional<String> token, Optional<Long> channelId) {
        Channel channel = new Channel();
        channel.setFeedType("rss_2.0");
        channel.setTitle("pt chart" + (channelId.orElse(0L)));
        channel.setDescription("bittorrent rss for " + username.orElse(""));
        channel.setLink("");
        channel.setUri("");
        channel.setGenerator("pt chat");
        LinkedList<Item> itemLinkedList = new LinkedList<>();
        if (username.isPresent() && token.isPresent() && channelId.isPresent()) {
            if (!username.get().equals("")) {
                ChannelEntity entity = channelEntityRepository.findByChannelIdAndOwnedUser_Username(channelId.get(), username.get());


                if (entity != null) { // avoid NPE
                    entity.getLinkSet().forEach(link -> {
                        Item item = new Item();
                        Enclosure enclosure = new Enclosure();
                        enclosure.setUrl(link.getUrl());
                        enclosure.setType("application/x-bittorrent");
                        item.setEnclosures(Collections.singletonList(enclosure));
                        itemLinkedList.add(item);
                    });
                }
                channel.setItems(itemLinkedList);
                return channel;
            }
        }
        return channel;
    }
}
