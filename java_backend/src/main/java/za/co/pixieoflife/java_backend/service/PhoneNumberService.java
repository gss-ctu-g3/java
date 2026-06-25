package za.co.pixieoflife.java_backend.service;

import org.springframework.stereotype.Service;

@Service

public class PhoneNumberService {
    public String sanitizePhoneNumberSa(String rawNumber) {

        if (rawNumber == null) {
            return null;
        }

        String trimmed = rawNumber.trim();
        if (trimmed.isEmpty()) {
            return null;
        }

        String sanitized;

        if (trimmed.startsWith("+27")) { // converts user input
            sanitized = trimmed.substring(1); // drops the "+", keep 27...

        } else if (trimmed.startsWith("0")) {
            sanitized = "27" + trimmed.substring(1); // swap leading 0 for 27

        } else if (trimmed.startsWith("27")) {
            sanitized = trimmed;

        } else {
            return null;
        }

        if (!isOnlyDigits(sanitized) || sanitized.length() != 11 || !sanitized.startsWith("27")) { // catches letters slipping through or wrong lengths
            return null;
        }

        return sanitized;
    }

    private boolean isOnlyDigits(String text) {
        if (text == null) {
            return false;
        }

        for (int i = 0; i < text.length(); i++) {
            if (!Character.isDigit(text.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public boolean validatePhoneNumberSa(String rawNumber) {  // Defaults to false so any unexpected failure never accidentally reports valid
        boolean isValid = false;

        try {
            String sanitized = sanitizePhoneNumberSa(rawNumber);

            if (sanitized != null) {
                isValid = true; // sanitize() already enforces all the rules
            }

        } catch (Exception e) {
            isValid = false; // defensive net
        }

        return isValid;
    }

}
