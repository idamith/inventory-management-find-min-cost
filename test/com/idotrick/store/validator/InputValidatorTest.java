package com.idotrick.store.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    @Test
    void getInstance() {
        InputValidator expected = InputValidator.getInstance();
        assertNotNull(expected);
    }

    @Test
    void validateFalse() {
        String[] input = "ITALY:B123AB1234567:IPHONE:20:IPOD:10".split(":");
        Boolean actual = InputValidator.getInstance().validate(input);
        assertFalse(actual);
    }

    @Test
    void validateTrueWithOptional() {
        String[] input = "BRAZIL:B123AB1234567:IPHONE:20:IPOD:10".split(":");
        Boolean actual = InputValidator.getInstance().validate(input);
        assertTrue(actual);
    }

    @Test
    void validateTrueWithNoOptional() {
        String[] input = "BRAZIL::IPHONE:20:IPOD:10".split(":");
        Boolean actual = InputValidator.getInstance().validate(input);
        assertTrue(actual);
    }

    @Test
    void validatePurchaseCountryFalse() {
        Boolean actual = InputValidator.getInstance().validatePurchaseCountry("ITALY");
        assertFalse(actual);
    }

    @Test
    void validatePurchaseCountryTrueBrazil() {
        Boolean actual = InputValidator.getInstance().validatePurchaseCountry("BRAZIL");
        assertTrue(actual);
    }

    @Test
    void validatePurchaseCountryTrueArgentina() {
        Boolean actual = InputValidator.getInstance().validatePurchaseCountry("ARGENTINA");
        assertTrue(actual);
    }


}