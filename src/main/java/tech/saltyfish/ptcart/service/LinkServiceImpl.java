package tech.saltyfish.ptcart.service;

import org.springframework.stereotype.Service;
import tech.saltyfish.ptcart.model.entity.ChannelEntity;
import tech.saltyfish.ptcart.model.entity.Link;
import tech.saltyfish.ptcart.repository.ChannelRepository;
import tech.saltyfish.ptcart.repository.LinkRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LinkServiceImpl implements LinkService {
    private final LinkRepository linkRepository;
    private final ChannelRepository channelRepository;

    public LinkServiceImpl(LinkRepository linkRepository, ChannelRepository channelRepository) {
        this.linkRepository = linkRepository;
        this.channelRepository = channelRepository;
    }


    @Override
    public List<ChannelEntity> addLink(String username, Long channelId, String url) {
        Link link = new Link();
        link.setUrl(url);
        Optional<ChannelEntity> oc = channelRepository.findByOwnedUser_Username(username)
                .stream()
                .filter(channel -> channel.getChannelId().equals(channelId))
                .findFirst();
        oc.ifPresent(link::setOwnedChannel);
        linkRepository.save(link);
        return channelRepository.findByOwnedUser_Username(username);
    }
}
