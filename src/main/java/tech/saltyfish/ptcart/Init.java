package tech.saltyfish.ptcart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.saltyfish.ptcart.service.AuthService;
import tech.saltyfish.ptcart.utils.RssTokenUtils;

@Configuration
public class Init {
    private static final Logger log = LoggerFactory.getLogger(Init.class);

    @Bean
    CommandLineRunner initDatabase(AuthService authServiceImpl) {
        return args -> {
            String password = RssTokenUtils.generateRssToken("admin", "password");
            if (authServiceImpl.registerUser("admin", password, "ROLE_USER") != null) {
                log.info("First Init: admin: {username: \"admin\", password: \"" + password + "\"}");
            }
            log.info("init complete!");

        };
    }
}
