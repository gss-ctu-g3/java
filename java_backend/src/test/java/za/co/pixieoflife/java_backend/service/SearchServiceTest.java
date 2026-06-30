package za.co.pixieoflife.java_backend.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchServiceTest {
    private final SearchService service = new SearchService();

    // ---------- Terms that should be valid ----------

    @Test
    void lowercaseLettersOnlyShouldBeValid() {
        assertTrue(service.validateSearchTerm("hello"));
    }

    @Test
    void lettersAndDigitsShouldBeValid() {
        assertTrue(service.validateSearchTerm("noma123"));
    }

    @Test
    void uppercaseShouldBeConvertedAndStillValid() { // "Noma123" should sanitize down to "noma123" and pass validation
        assertTrue(service.validateSearchTerm("Noma123"));
        assertEquals("noma123", service.sanitizeSearchTerm("Noma123"));
    }

    @Test
    void singleCharacterShouldBeValid() {
        assertTrue(service.validateSearchTerm("a"));// minimum allowed length is 1
    }

    @Test
    void twentyCharactersShouldBeValid() {
        assertTrue(service.validateSearchTerm("abcde12345abcde12345"));// maximum allowed length is 20
    }

    // ---------- Terms that should be invalid ----------

    @Test
    void termWithSymbolsShouldBeInvalid() {
        assertFalse(service.validateSearchTerm("hello!"));
        assertFalse(service.validateSearchTerm("noma_123"));
    }

    @Test
    void termWithSpacesShouldBeInvalid() {
        assertFalse(service.validateSearchTerm("hello world"));
    }

    @Test
    void termOver20CharactersShouldBeInvalid() {
        assertFalse(service.validateSearchTerm("abcde12345abcde123456"));// 21 characters - one too many
    }

    // ---------- Edge cases ----------

    @Test
    void nullInputShouldBeInvalid() {
        assertFalse(service.validateSearchTerm(null));
        assertNull(service.sanitizeSearchTerm(null));
    }

    @Test
    void emptyStringShouldBeInvalid() {
        assertFalse(service.validateSearchTerm(""));
        assertNull(service.sanitizeSearchTerm(""));
    }

    @Test
    void whitespaceOnlyShouldBeInvalid() {
        assertFalse(service.validateSearchTerm(" "));
        assertNull(service.sanitizeSearchTerm(" "));
    }
}
