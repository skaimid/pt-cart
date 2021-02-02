package tech.saltyfish.ptcart.service;

import tech.saltyfish.ptcart.model.entity.ChannelEntity;

import java.util.List;

public interface LinkService {
    List<ChannelEntity> addLink(String username, Long channelId, String url);
}
