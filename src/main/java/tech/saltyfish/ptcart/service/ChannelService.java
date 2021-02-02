package tech.saltyfish.ptcart.service;

import tech.saltyfish.ptcart.model.entity.ChannelEntity;

import java.util.List;

public interface ChannelService {
    List<ChannelEntity> getChannelByUsername(String username);

    List<ChannelEntity> deleteChannelById(String username, long cid);

    List<ChannelEntity> addChannel(String username, ChannelEntity channel);
}
