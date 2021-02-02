package tech.saltyfish.ptcart.service;

import com.rometools.rome.feed.rss.Channel;

import java.util.Optional;

public interface RssService {
    Channel getChannelByUsernameAndChannelId(Optional<String> username, Optional<String> token, Optional<Long> channelId);
}
