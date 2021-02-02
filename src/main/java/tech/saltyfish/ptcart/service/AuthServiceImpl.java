package tech.saltyfish.ptcart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.saltyfish.ptcart.model.entity.User;
import tech.saltyfish.ptcart.repository.UserRepository;
import tech.saltyfish.ptcart.utils.RssTokenUtils;

import java.security.NoSuchAlgorithmException;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String registerUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        // 记得注册的时候把密码加密一下
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRole(role);
        User save = userRepository.save(user);
        return save.toString();
    }

    @Override
    public User updatePassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    @Override
    public String getToken(String username) {
        User user = userRepository.findByUsername(username);
        if (user.getToken() == null || user.getToken().length() != 32) {
            return generateToken(username);
        } else {
            return user.getToken();
        }
    }

    @Override
    public String generateToken(String username) {
        User user = userRepository.findByUsername(username);
        try {
            user.setToken(RssTokenUtils.generateRssToken(user.getUsername(), user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        userRepository.save(user);
        return user.getToken();
    }

    @Override
    public User validateToken(String username) {
        return userRepository.findByUsername(username);
    }
}
