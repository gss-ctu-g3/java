package za.co.pixieoflife.java_backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.co.pixieoflife.java_backend.service.PhoneNumberService;

@RestController

public class PhoneNumberController {
    private final PhoneNumberService phoneNumberService;

    @Autowired
    public PhoneNumberController(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @GetMapping("/validate/phone_number_sa") // Get /validate/phone number sa number.07......
    public boolean validatePhoneNumberSa(@RequestParam String number) {
        boolean result = false; // worst case first

        try {
            result = phoneNumberService.validatePhoneNumberSa(number);
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

    @GetMapping("/sanitize/phone_number_sa")// GET /sanitize/phone_number_sa?number=0821234567
    public String sanitizePhoneNumberSa(@RequestParam String number) {
        String result = "Could not sanitize phone number"; // worst case first

        try {
            String sanitized = phoneNumberService.sanitizePhoneNumberSa(number);

            if (sanitized != null) {
                result = sanitized;
            }

        } catch (Exception e) {
            result = "Unexpected error during sanitization";
        }

        return result;
    }

    @GetMapping("/validate/phone_number_au") // Unsupported country formats, just return a clear message instead of crashing
    public String validatePhoneNumberAu() {
        return "Not supported format yet";
    }

    @GetMapping("/validate/phone_number_us")
    public String validatePhoneNumberUs() {
        return "Not supported format yet";
    }

    @GetMapping("/validate/phone_number_uk")
    public String validatePhoneNumberUk() {
        return "Not supported format yet";
    }

}
