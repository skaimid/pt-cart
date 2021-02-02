package tech.saltyfish.ptcart.service;

import org.springframework.stereotype.Service;
import tech.saltyfish.ptcart.model.entity.ChannelEntity;
import tech.saltyfish.ptcart.repository.ChannelRepository;
import tech.saltyfish.ptcart.repository.UserRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    public ChannelServiceImpl(ChannelRepository channelRepository, UserRepository userRepository) {
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ChannelEntity> getChannelByUsername(String username) {
        return channelRepository.findByOwnedUser_Username(username);
    }

    @Override
    public List<ChannelEntity> deleteChannelById(String username, long cid) {
        if (channelRepository.findByOwnedUser_Username(username).stream().anyMatch(channel -> channel.getChannelId() == cid)) {
            channelRepository.deleteById(cid);
            return channelRepository.findByOwnedUser_Username(username);
        } else {
            return null;
        }
    }

    @Override
    public List<ChannelEntity> addChannel(String username, ChannelEntity channel) {
        channel.setOwnedUser(userRepository.findByUsername(username));
        channel.setLinkSet(new LinkedList<>());
        channelRepository.save(channel);
        return channelRepository.findByOwnedUser_Username(username);
    }
}
