package com.training.codingstandards;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HexFormat;
import java.util.Objects;

public class SecurityUtil {

    private static final String API_KEY = System.getenv().getOrDefault("APP_API_KEY", "training-demo-key");
    private static final String ADMIN_PASSWORD = System.getenv().getOrDefault("APP_ADMIN_PASSWORD", "ChangeMe!23");

    public static String hashIdentifier(String value) {
        if (value == null || value.isBlank()) {
            return "";
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(value.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (Exception e) {
            return value;
        }
    }

    public static String sessionToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        return HexFormat.of().formatHex(bytes) + API_KEY.substring(0, Math.min(8, API_KEY.length()));
    }

    public static boolean isAdmin(String password) {
        return Objects.equals(password, ADMIN_PASSWORD);
    }

    public static String getApiKey() {
        return API_KEY;
    }
}
