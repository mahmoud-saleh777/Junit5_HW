package main.najah.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserService {

    UserService userService = new UserService();

    @Test
    @DisplayName("Valid email should return true")
    void testValidEmail_valid() {
        assertTrue(userService.isValidEmail("test@example.com"));
    }

    @Test
    @DisplayName("Null email should return false")
    void testValidEmail_null() {
        assertFalse(userService.isValidEmail(null));
    }

    @Test
    @DisplayName("Email without @ should return false")
    void testValidEmail_missingAt() {
        assertFalse(userService.isValidEmail("testexample.com"));
    }

    @Test
    @DisplayName("Email without dot should return false")
    void testValidEmail_missingDot() {
        assertFalse(userService.isValidEmail("test@examplecom"));
    }

    @ParameterizedTest
    @CsvSource({
            "test@example.com,true",
            "user@mail.org,true",
            "invalidemail,false",
            "test@com,false"
    })
    @DisplayName("Parameterized email validation")
    void testValidEmail_parameterized(String email, boolean expected) {
        assertEquals(expected, userService.isValidEmail(email));
    }

    @Test
    @DisplayName("Correct credentials should authenticate")
    void testAuthenticate_valid() {
        assertTrue(userService.authenticate("admin", "1234"));
    }

    @Test
    @DisplayName("Wrong username should fail authentication")
    void testAuthenticate_wrongUsername() {
        assertFalse(userService.authenticate("user", "1234"));
    }

    @Test
    @DisplayName("Wrong password should fail authentication")
    void testAuthenticate_wrongPassword() {
        assertFalse(userService.authenticate("admin", "wrong"));
    }

    @Test
    @Timeout(1)
    @DisplayName("Authentication should be fast")
    void testAuthenticate_timeout() {
        assertTrue(userService.authenticate("admin", "1234"));
    }
}