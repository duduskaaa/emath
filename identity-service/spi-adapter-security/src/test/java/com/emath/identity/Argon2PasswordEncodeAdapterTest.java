package com.emath.identity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Argon2PasswordEncodeAdapterTest {

    private Argon2PasswordEncoderAdapter passwordEncoderAdapter;
    private String rawPassword = "123QweTro";

    @BeforeEach
    void setUp() {
        passwordEncoderAdapter = new Argon2PasswordEncoderAdapter();
    }

    @Test
    @DisplayName("Successful password verify with hash")
    void shouldMatchPasswordWithHash() {
        String passwordHash = passwordEncoderAdapter.encode(rawPassword);

        assertTrue(passwordEncoderAdapter.verify(rawPassword, passwordHash));
    }

    @Test
    @DisplayName("Successful unmatches password with hash")
    void shouldUnmatchPasswordWithHash() {
        String passwordHash = passwordEncoderAdapter.encode(rawPassword);

        assertFalse(passwordEncoderAdapter.verify("124QweTro", passwordHash));
    }

    @Test
    @DisplayName("Successful create different hash for one password")
    void shouldDifferHashesForOnePassword() {
        String passwordHash = passwordEncoderAdapter.encode(rawPassword);
        String passwordHash2 = passwordEncoderAdapter.encode(rawPassword);

        assertNotEquals(passwordHash, passwordHash2);
    }
}
