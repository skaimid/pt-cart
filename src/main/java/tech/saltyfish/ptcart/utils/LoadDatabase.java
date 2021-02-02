package tech.saltyfish.ptcart.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.saltyfish.ptcart.repository.ChannelRepository;
import tech.saltyfish.ptcart.repository.LinkRepository;
import tech.saltyfish.ptcart.repository.UserRepository;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(LinkRepository linkRepository, ChannelRepository channelRepository, UserRepository userRepository) {
        return args -> {
            System.out.println("init complete");

        };
    }
}
