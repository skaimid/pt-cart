package tech.saltyfish.ptcart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.saltyfish.ptcart.service.UserRepository;

import java.util.Optional;

@RestController
public class RssController {
    private final UserRepository userRepository;

    public RssController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/rss")
    String rss(@RequestParam("username") Optional<String> username,
               @RequestParam("token") Optional<String> token,
               @RequestParam("channel") Optional<Integer> channel) {

        if (username.isPresent() && token.isPresent() && channel.isPresent()) {
            if (!username.get().equals("")) {
                userRepository.findByUsername(username.get());
                return "rss";
            }
        }
        return "fails";
    }
}
