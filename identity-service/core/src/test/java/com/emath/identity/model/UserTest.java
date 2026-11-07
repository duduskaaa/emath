package com.emath.identity.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private UUID id = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
    private String firstName = "First";
    private String lastName = "Last";
    private String email = "firstlast@gmail.com";
    private String passwordHash = "<hash>";
    private Set<Role> roles = Set.of(Role.STUDENT);

    @Test
    @DisplayName("Rejects null id")
    void shouldThrowNullPointerException_whenIdNull() {
        assertThrows(NullPointerException.class,
                () -> new User(null, firstName, lastName, email, passwordHash, roles)
        );
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            " ", "", "\t"
    })
    @DisplayName("Rejects null or blank first name")
    void shouldThrowsIllegalArgumentException_whenFirstNameBlank(String firstName) {
        assertThrows(IllegalArgumentException.class,
                () -> new User(id, firstName, lastName, email, passwordHash, roles)
        );
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            " ", "", "\t"
    })
    @DisplayName("Rejects null or blank last name")
    void shouldThrowsIllegalArgumentException_whenLastNameBlank(String lastName) {
        assertThrows(IllegalArgumentException.class,
                () -> new User(id, firstName, lastName, email, passwordHash, roles)
        );
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "", " ", "\t"
    })
    @DisplayName("Rejects null or blank email")
    void shouldThrowsIllegalArgumentException_whenEmailBlank(String email) {
        assertThrows(IllegalArgumentException.class,
                () -> new User(id, firstName, lastName, email, passwordHash, roles)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "firstlastgmail.com",
            "firstlast@gmail",
            "fisrt%^@gmail.com",
            "first@"
    })
    @DisplayName("Rejects email with invalid format")
    void shouldThrowsIllegalArgumentException_whenEmailFormatNotValid(String email) {
        assertThrows(IllegalArgumentException.class,
                () -> new User(id, firstName, lastName, email, passwordHash, roles)
        );
    }

    @Test
    @DisplayName("Rejects empty roles")
    void shouldThrowsIllegalArgumentException_whenRolesEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new User(id, firstName, lastName, email, passwordHash, Set.of()));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            "", " ", "\t"
    })
    @DisplayName("Rejects empty passwordHash")
    void shouldThrowsIllegalArgumentException_whenPasswordHashBlank(String passwordHash) {
        assertThrows(IllegalArgumentException.class,
                () -> new User(id, firstName, lastName, email, passwordHash, roles));
    }

    @Test
    void shouldNormalizeEmailToLowerCase() {
        email = "FirstLast@Gmail.com";
        User user = new User(id, firstName, lastName, email, passwordHash, roles);
        assertEquals(
                "firstlast@gmail.com",
                user.getEmail()
        );
    }

    @Test
    @DisplayName("Does not reflect later changes of the source roles")
    void shouldNotReflectChanges_whenSourceRolesModified() {
        Set<Role> roleSource = new HashSet<>(Set.of(Role.STUDENT));
        User user = new User(id, firstName, lastName, email, passwordHash, roleSource);

        roleSource.add(Role.ADMIN);
        assertEquals(
                Set.of(Role.STUDENT),
                user.getRoles()
        );
    }

    @Test
    @DisplayName("Exposes roles as unmodifiable")
    void shouldThrowUnsupportedOperationException_whenAddingRole() {
        User user = new User(id, firstName, lastName, email, passwordHash, roles);

        assertThrows(
                UnsupportedOperationException.class,
                () -> user.getRoles().add(Role.ADMIN)
        );
    }

    @ParameterizedTest
    @MethodSource("nullRolesCases")
    @DisplayName("Rejects null roles and roles with null elements")
    void shouldThrowExceptionWhenRolesContainNull(Set<Role> roles) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new User(id, firstName, lastName, email, passwordHash, roles)
        );
    }

    static Stream<Set<Role>> nullRolesCases() {
        Set<Role> rolesWithNull = new HashSet<>();
        rolesWithNull.add(null);

        return Stream.of(
                null,
                rolesWithNull
        );
    }

    @Test
    @DisplayName("Creates User from valid data")
    void shouldCreateUser_whenAllFieldsValid() {
        User user = new User(id, firstName, lastName, email, passwordHash, roles);

        assertEquals(id, user.getId());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());   // нормализованный email
        assertEquals(passwordHash, user.getPasswordHash());
        assertEquals(roles, user.getRoles());

    }

    @ParameterizedTest
    @ValueSource(strings = {
            "first.last@gmail.com",
            "fisrt+last@mail.com",
            "first-last@gmail.com",
            "fisrt_last@sub.domain.org",
            "123@456.io",
            "a@b.co"
    })
    @DisplayName("Accepts valid email")
    void shouldCreateUser_whenEmailValid(String email) {
        assertDoesNotThrow(
                () -> new User(id, firstName, lastName, email, passwordHash, roles));
    }
}
