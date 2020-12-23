package tech.saltyfish.ptcart.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class RssTokenUtils {
    public static String generateRssToken(String username, String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        StringBuilder sb = new StringBuilder();

        // hash 过程
        messageDigest.reset();
        messageDigest.update(sb
                .append(username)
                .append(password)
                .append(new Random().nextInt())
                .toString().getBytes());
        byte[] digest = messageDigest.digest();


        BigInteger bigInteger = new BigInteger(1, digest);
        StringBuilder hashStringBuilder = new StringBuilder(bigInteger.toString(16));

        while (hashStringBuilder.length() < 32) {
            hashStringBuilder.insert(0, "0");
        }
        return hashStringBuilder.toString();
    }

}
