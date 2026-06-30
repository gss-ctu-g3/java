package za.co.pixieoflife.java_backend.service;

import org.springframework.stereotype.Service;

@Service
public class SearchService {
    public String sanitizeSearchTerm(String rawTerm) {// Cleans up a raw search term: trims spaces and converts to lowercase.

        if (rawTerm == null) {
            return null;
        }

        String trimmed = rawTerm.trim();
        if (trimmed.isEmpty()) {
            return null;
        }

        return trimmed.toLowerCase(); // treats uppercase and lowercase the same
    }

    private boolean isOnlyLettersAndDigits(String text) { // Checks if a String contains only lowercase letters a-z and digits 0-9
        if (text == null) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            char currentCharacter = text.charAt(i);
            boolean isLowercaseLetter = currentCharacter >= 'a' && currentCharacter <= 'z';
            boolean isDigit = Character.isDigit(currentCharacter);

            if (!isLowercaseLetter && !isDigit) {
                return false;
            }
        }
        return true;
    }

    public boolean validateSearchTerm(String rawTerm) { //Returns true/false only, Defaults to false so any unexpected failure never reports valid by mistake.
        boolean isValid = false;

        try {
            String sanitized = sanitizeSearchTerm(rawTerm);

            if (sanitized != null
                    && isOnlyLettersAndDigits(sanitized)
                    && sanitized.length() >= 1
                    && sanitized.length() <= 20) {
                isValid = true;
            }

        } catch (Exception e) {
            isValid = false; // defensive net
        }

        return isValid;
    }
}
