package tech.saltyfish.ptcart.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

class RssTokenUtilsTest {
    @Test
    void Test() {
        String username = "admin";
        String password = "password";
        try {
            String s1 = RssTokenUtils.generateRssToken(username, password);
            String s2 = RssTokenUtils.generateRssToken(username, password);
            Assertions.assertNotEquals(s2, s1);
        } catch (NoSuchAlgorithmException e) {
            Assertions.fail();
            e.printStackTrace();
        }
    }
}