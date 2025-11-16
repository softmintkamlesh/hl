package com.hypershop.utils;

import java.security.SecureRandom;
import java.time.Instant;

public class GeneratorUtil {

    private static final char[] ALPHABET = "0123456789ABCDEFGHJKMNPQRSTVWXYZ".toCharArray();
    private static final SecureRandom RANDOM = new SecureRandom();

    private GeneratorUtil() {}

    public static String ulid() {
        long time = Instant.now().toEpochMilli();
        char[] id = new char[26];
        for (int i = 9; i >= 0; i--) {
            id[i] = ALPHABET[(int) (time & 31)];
            time >>>= 5;
        }
        for (int i = 10; i < 26; i++) {
            id[i] = ALPHABET[RANDOM.nextInt(ALPHABET.length)];
        }
        return new String(id);
    }


    public static String ulidWithPrefix(String prefix) {
        return prefix + "_" + ulid();
    }

    public static String generateOtp() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}