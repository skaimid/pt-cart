package tech.saltyfish.ptcart.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tech.saltyfish.ptcart.model.entity.User;
import tech.saltyfish.ptcart.service.UserRepository;
import tech.saltyfish.ptcart.utils.RssTokenUtils;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // 为了减少篇幅就不写service接口了

    private final UserRepository userRepository;


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String, String> registerUser) {
        User user = new User();
        user.setUsername(registerUser.get("username"));
        // 记得注册的时候把密码加密一下
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.get("password")));
        user.setRole("ROLE_USER");
        User save = userRepository.save(user);
        return save.toString();
    }

    @GetMapping("/validate")
    public User validateToken(Principal principal) {
        String name = principal.getName();
        return userRepository.findByUsername(name);
    }

    @GetMapping("/token")
    public Map<String, String> getToken(Principal principal) {
        HashMap<String, String> rs = new HashMap<>();
        User user = userRepository.findByUsername(principal.getName());
        if (user.getToken() == null || user.getToken().length() != 32) {
            try {
                user.setToken(RssTokenUtils.generateRssToken(user.getUsername(), user.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            userRepository.save(user);
        }
        rs.put("token", user.getToken());
        return rs;
    }

    @GetMapping("/updateToken")
    public Map<String, String> updateToken(Principal principal) {
        HashMap<String, String> rs = new HashMap<>();
        User user = userRepository.findByUsername(principal.getName());

        try {
            user.setToken(RssTokenUtils.generateRssToken(user.getUsername(), user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        userRepository.save(user);

        rs.put("token", user.getToken());
        return rs;
    }
}
