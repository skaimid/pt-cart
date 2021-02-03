package tech.saltyfish.ptcart.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.saltyfish.ptcart.service.AuthService;

@Configuration
public class Init {
    private static final Logger log = LoggerFactory.getLogger(Init.class);

    @Bean
    CommandLineRunner initDatabase(AuthService authServiceImpl) {
        return args -> {
            String password = RssTokenUtils.generateRssToken("admin", "password");
            authServiceImpl.registerUser("admin", password, "ROLE_USER");
            log.info("admin:{ username: \"admin\", password: \"" + password + "\"}");
            log.info("init complete!");

        };
    }
}
