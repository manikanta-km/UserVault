package com.makersharks.uservault.service.hashingutility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encrypt(String userPassword) {
        return encoder.encode(userPassword);
    }
}
