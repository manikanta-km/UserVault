package com.makersharks.uservault.service.hashingutility;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordEncryptorTest {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    void testEncrypt() {
        // Given
        String rawPassword = "password123";

        // When
        String encryptedPassword = PasswordEncryptor.encrypt(rawPassword);

        // Then
        assertTrue(encoder.matches(rawPassword, encryptedPassword),
                "The encrypted password should match the raw password.");
    }

    @Test
    void testEncryptDifferentPasswords() {
        // Given
        String password1 = "password123";
        String password2 = "differentPassword";

        // When
        String encryptedPassword1 = PasswordEncryptor.encrypt(password1);
        String encryptedPassword2 = PasswordEncryptor.encrypt(password2);

        // Then
        assertTrue(encoder.matches(password1, encryptedPassword1),
                "The encrypted password1 should match password1.");
        assertTrue(encoder.matches(password2, encryptedPassword2),
                "The encrypted password2 should match password2.");
    }

    @Test
    void testEncryptSamePasswordProducesDifferentHashes() {
        // Given
        String password = "password123";

        // When
        String encryptedPassword1 = PasswordEncryptor.encrypt(password);
        String encryptedPassword2 = PasswordEncryptor.encrypt(password);

        // Then
        assertTrue(encoder.matches(password, encryptedPassword1),
                "The first encrypted password should match the raw password.");
        assertTrue(encoder.matches(password, encryptedPassword2),
                "The second encrypted password should match the raw password.");
        assertTrue(!encryptedPassword1.equals(encryptedPassword2),
                "Two encryptions of the same password should produce different hashes.");
    }
}
