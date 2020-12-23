package tech.saltyfish.ptcart.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.saltyfish.ptcart.service.ChannelRepository;
import tech.saltyfish.ptcart.service.LinkRepository;
import tech.saltyfish.ptcart.service.UserRepository;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(LinkRepository linkRepository, ChannelRepository channelRepository, UserRepository userRepository) {
        return args -> {
            System.out.println("init complete");
//            User user = new User("user","user");
//            Channel ch1 = new Channel("ch1",user);
//            Channel ch2 = new Channel("ch2",user);
//
//            log.info("Preloading " + userRepository.save(user));
//            log.info("Preloading " + channelRepository.save(ch1));
//            log.info("Preloading " + channelRepository.save(ch2));
//            log.info("Preloading " + linkRepository.save(new Link("a",ch1)));
//            log.info("Preloading " + linkRepository.save(new Link("b",ch1)));
//            log.info("Preloading " + linkRepository.save(new Link("c",ch2)));
//            log.info("Preloading " + linkRepository.save(new Link("d",ch2)));
        };
    }
}
