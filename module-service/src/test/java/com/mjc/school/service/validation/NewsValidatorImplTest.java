package com.mjc.school.service.validation;

import com.mjc.school.service.DTO.NewsBriefDTO;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class NewsValidatorImplTest {
    NewsValidatorImpl validator = new NewsValidatorImpl();

    @Test
    public void testShortTitle() {
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO("Shrt", "Valid content", 123);
        Set<String> violations = validator.validate(newsBriefDTO);
        assertTrue(violations.contains("Title should have length from 5 to 30."));
    }

    @Test
    public void testLongTitle() {
        String longTitle = "1234567890".repeat(3) + "1";
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO(longTitle, "Valid content", 123);
        Set<String> violations = validator.validate(newsBriefDTO);
        assertTrue(violations.contains("Title should have length from 5 to 30."));
    }

    @Test
    public void testShortContent() {
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO("Valid title", "Shrt", 123);
        Set<String> violations = validator.validate(newsBriefDTO);
        assertTrue(violations.contains("Content should have length from 5 to 255."));
    }

    @Test
    public void testLongContent() {
        String longContent = "1234567890".repeat(25) + "123456";
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO("Valid title", longContent, 123);
        Set<String> violations = validator.validate(newsBriefDTO);
        assertTrue(violations.contains("Content should have length from 5 to 255."));
    }

    @Test
    public void testOtherPropertiesNotViolated() {
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO("Valid title", "Valid content", 123);
        Set<String> violations = validator.validate(newsBriefDTO);
        assertFalse(violations.size() > 0);
    }

    @Test
    public void testViolationSetContainsOneErrorMessagePerType() {
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO("Shrt", "Shrt", 123);
        Set<String> violations = validator.validate(newsBriefDTO);
        assertEquals(2, violations.size());
        assertTrue(violations.contains("Title should have length from 5 to 30."));
        assertTrue(violations.contains("Content should have length from 5 to 255."));
    }

    @Test
    public void testMaxBoundaryValues() {
        String maxTitle = "123456789012345678901234567890"; // 30 characters
        String maxContent = "1234567890".repeat(25)+"12345"; // 255 characters
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO(maxTitle, maxContent, 123);
        Set<String> violations = validator.validate(newsBriefDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testMinBoundaryValues() {
        String maxTitle = "12345"; // 5 characters
        String maxContent = "12345"; // 5 characters
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO(maxTitle, maxContent, 123);
        Set<String> violations = validator.validate(newsBriefDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testNullTitle() {
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO(null, "Valid content", 123);
        Set<String> violations = validator.validate(newsBriefDTO);
        assertFalse(violations.isEmpty());
        assertTrue(violations.contains("Title should have length from 5 to 30."));
    }

    @Test
    public void testNullContent() {
        NewsBriefDTO newsBriefDTO = new NewsBriefDTO("Valid title", null, 123);
        Set<String> violations = validator.validate(newsBriefDTO);
        assertFalse(violations.isEmpty());
        assertTrue(violations.contains("Content should have length from 5 to 255."));
    }

    @Test
    public void TestNullObject() {
        Set<String> violations = validator.validate(null);
        assertTrue(violations.contains("All data is required."));
    }

}