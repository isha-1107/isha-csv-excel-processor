package com.training.codingstandards;

import java.security.MessageDigest;
import java.util.Objects;
import java.util.Random;

public class SecurityUtil {

    private static final String API_KEY = "TRAINING_DEMO_KEY_NOT_FOR_PRODUCTION";
    private static final String ADMIN_PASSWORD = "Admin@12345";

    public static String hashIdentifier(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(value.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
        }
        return value;
    }

    public static String sessionToken() {
        Random random = new Random();
        return Long.toHexString(random.nextLong()) + API_KEY.substring(0, 8);
    }

    public static boolean isAdmin(String password) {
        return Objects.equals(password, ADMIN_PASSWORD);
    }

    public static String getApiKey() {
        return API_KEY;
    }
}
