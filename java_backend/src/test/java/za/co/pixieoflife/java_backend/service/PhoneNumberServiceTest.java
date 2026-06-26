package za.co.pixieoflife.java_backend.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhoneNumberServiceTest {
    private final PhoneNumberService service = new PhoneNumberService();

    // Numbers that should be valid

    @Test
    void numberStartingWithZeroShouldBeValid() {
        assertTrue(service.validatePhoneNumberSa("0821234567"));
    }

    @Test
    void numberStartingWithPlus27ShouldBeValid() {
        assertTrue(service.validatePhoneNumberSa("+27821234567"));
    }

    @Test
    void numberAlreadyStartingWith27ShouldBeValid() {
        assertTrue(service.validatePhoneNumberSa("27821234567"));
    }

    @Test
    void allThreeFormatsShouldSanitizeToTheSameNumber() {
        // Different input formats, same expected output - the actual point of sanitizing
        assertEquals("27821234567", service.sanitizePhoneNumberSa("0821234567"));
        assertEquals("27821234567", service.sanitizePhoneNumberSa("+27821234567"));
        assertEquals("27821234567", service.sanitizePhoneNumberSa("27821234567"));
    }

    // Numbers that should be invalid

    @Test
    void numberWithLettersShouldBeInvalid() {
        assertFalse(service.validatePhoneNumberSa("08212ABC67"));
        assertNull(service.sanitizePhoneNumberSa("08212ABC67"));
    }

    @Test
    void numberTooShortShouldBeInvalid() {
        assertFalse(service.validatePhoneNumberSa("12345"));
    }

    @Test
    void numberWithWrongLengthShouldBeInvalid() {
        assertFalse(service.validatePhoneNumberSa("27821234"));
    }

    // ---------- Edge cases ----------

    @Test
    void nullInputShouldBeInvalid() {
        assertFalse(service.validatePhoneNumberSa(null));
        assertNull(service.sanitizePhoneNumberSa(null));
    }

    @Test
    void emptyStringShouldBeInvalid() {
        assertFalse(service.validatePhoneNumberSa(""));
        assertNull(service.sanitizePhoneNumberSa(""));
    }

    @Test
    void whitespaceOnlyShouldBeInvalid() {
        assertFalse(service.validatePhoneNumberSa(" "));
        assertNull(service.sanitizePhoneNumberSa(" "));
    }
}

