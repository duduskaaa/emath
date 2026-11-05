package com.emath.identity.security;

public interface PasswordEncoder {
    String encode(String rawPassword);
    boolean verify(String rawPassword, String passwordHash);
}
