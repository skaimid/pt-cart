package tech.saltyfish.ptcart.controller;

import org.springframework.web.bind.annotation.*;
import tech.saltyfish.ptcart.model.entity.User;
import tech.saltyfish.ptcart.service.AuthService;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authServiceImpl) {
        this.authService = authServiceImpl;
    }


    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String, String> registerUser) {
        return authService.registerUser(registerUser.get("username"), registerUser.get("password"), "ROLE_USER");
    }

    @PostMapping("/updatePassword")
    public User updatePassword(@RequestBody Map<String, String> newPassword, Principal principal) {
        return authService.updatePassword(principal.getName(), newPassword.get("password"));
    }

    @GetMapping("/validate")
    public User validateToken(Principal principal) {
        return authService.validateToken(principal.getName());
    }

    @GetMapping("/token")
    public Map<String, String> getToken(Principal principal) {
        HashMap<String, String> rs = new HashMap<>();
        rs.put("token", authService.getToken(principal.getName()));
        return rs;
    }

    @GetMapping("/updateToken")
    public Map<String, String> updateToken(Principal principal) {
        HashMap<String, String> rs = new HashMap<>();
        rs.put("token", authService.generateToken(principal.getName()));
        return rs;
    }
}
