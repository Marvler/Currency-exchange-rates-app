package com.sda.currencyexchangeapp.service;

import com.sda.currencyexchangeapp.model.exception.NoResultException;
import com.sda.currencyexchangeapp.service.validator.Validation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ValidationTest {

    Validation validation;

    @Autowired
    public ValidationTest(Validation validation) {
        this.validation = validation;
    }

    @Test
    public void shouldCheckIfTheBaseAndTargetCurrencyIsValid() {
        boolean result = validation.validateIfCurrencyExists("PLN", "USD");

        assertThat(result).isTrue();
    }

    @Test
    public void shouldCheckIfTheBaseAndTargetCurrencyIsNotValid1() {
        boolean result = validation.validateIfCurrencyExists("ABC", "XYZ");

        assertThat(result).isFalse();
    }

    @Test
    public void shouldCheckIfTheBaseAndTargetCurrencyIsNotValid2() {
        boolean result = validation.validateIfCurrencyExists("123", "987");

        assertThat(result).isFalse();
    }

    @Test
    public void shouldCheckIfTheBaseAndTargetCurrencyIsNotValid3() {
        boolean result = validation.validateIfCurrencyExists("american dollar", "zloty");

        assertThat(result).isFalse();
    }


    /**
     * Method under test: {@link Validation#validateIfCurrencyExists(String, String)}
     */
    @Test
    void testValidateIfCurrencyExists() {
        assertFalse(this.validation.validateIfCurrencyExists("Base", "Target"));
        assertFalse(this.validation.validateIfCurrencyExists("src/main/resources/currency.json", "Target"));
    }

    /**
     * Method under test: {@link Validation#validateDateFormat(String[])}
     */
    @Test
    void testValidateDateFormat() {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by validateDateFormat(String[])
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        this.validation.validateDateFormat("2020-03-01");
    }

    /**
     * Method under test: {@link Validation#validateDateFormat(String[])}
     */
    @Test
    void testValidateDateFormat2() {
        assertThrows(NoResultException.class, () -> this.validation.validateDateFormat("2020/03/01"));
    }



}