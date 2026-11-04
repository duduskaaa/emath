package com.emath.identity.model;

import lombok.Getter;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

@Getter
public class User {
    private final UUID id;
    private final String firstName;
    private final String lastName;

    private final String email;
    private final String passwordHash;
    private final Set<Role> roles;

    public User(UUID id, String firstName, String lastName, String email, String passwordHash, Set<Role> roles) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }

        email = email.toLowerCase();
        if (!isValidEmail(email))
            throw new IllegalArgumentException("Email format is wrong");
        this.email = email;

        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or blank");
        }
        this.firstName = firstName;

        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }
        this.lastName = lastName;

        if (!isValidRoles(roles))
            throw new IllegalArgumentException("Roles cannot be null or empty");
        this.roles = Set.copyOf(roles);

        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("PasswordHash must not be empty");
        }
        this.passwordHash = passwordHash;
    }

    private final static Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidRoles(Set<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return false;
        }

        for (Role role : roles) {
            if (role == null) {
                return false;
            }
        }
        return true;
    }
}
