package za.co.pixieoflife.java_backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import za.co.pixieoflife.java_backend.service.SearchService;

@RestController
public class SearchController {
    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/validate/search")
    public boolean validateSearch(@RequestParam String term) {
        boolean result = false; // worst case first

        try {
            result = searchService.validateSearchTerm(term);
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

    @GetMapping("/sanitize/search")
    public String sanitizeSearch(@RequestParam String term) {
        String result = "Could not sanitize search term"; // worst case first

        try {
            String sanitized = searchService.sanitizeSearchTerm(term);

            if (sanitized != null) {
                result = sanitized;
            }

        } catch (Exception e) {
            result = "Unexpected error during sanitization";
        }

        return result;
    }
}
