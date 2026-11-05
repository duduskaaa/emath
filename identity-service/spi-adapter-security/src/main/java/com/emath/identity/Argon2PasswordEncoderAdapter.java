package com.emath.identity;

import com.emath.identity.security.PasswordEncoder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class Argon2PasswordEncoderAdapter implements PasswordEncoder {

    private final static Argon2PasswordEncoder encoder =
            Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    @Override
    public String encode(String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean verify(String rawPassword, String passwordHash) {
        return encoder.matches(rawPassword, passwordHash);
    }
}
