package tech.saltyfish.ptcart.controller;

import com.rometools.rome.feed.rss.Channel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.saltyfish.ptcart.service.RssService;

import java.util.Optional;

@RestController
public class RssController {

    private final RssService rssServiceImpl;

    public RssController(RssService rssServiceImpl) {
        this.rssServiceImpl = rssServiceImpl;
    }


    @RequestMapping("/rss")
    Channel rss(@RequestParam("username") Optional<String> username,
                @RequestParam("token") Optional<String> token,
                @RequestParam("channel") Optional<Long> channelId) {
        return rssServiceImpl.getChannelByUsernameAndChannelId(username, token, channelId);
    }
}
