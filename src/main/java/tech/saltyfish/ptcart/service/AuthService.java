package tech.saltyfish.ptcart.service;

import tech.saltyfish.ptcart.model.entity.User;

public interface AuthService {
    String registerUser(String username, String password, String Role);

    User updatePassword(String username, String newPassword);

    String getToken(String username);

    String generateToken(String username);

    User validateToken(String username);
}
