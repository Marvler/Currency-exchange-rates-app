package com.sda.currencyexchangeapp.service;

import com.sda.currencyexchangeapp.service.validator.Validation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

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

}