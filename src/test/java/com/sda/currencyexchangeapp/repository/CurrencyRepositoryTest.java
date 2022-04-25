package com.sda.currencyexchangeapp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModelDto;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CurrencyRepositoryTest {
    @Autowired
    private CurrencyRepository currencyRepository;

    /**
     * Method under test: {@link CurrencyRepository#findByDate(LocalDate)}
     */
    @Test
    void testFindByDate() {
        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        this.currencyRepository.save(currencyExchangeRateModelDto);
        this.currencyRepository.save(currencyExchangeRateModelDto1);
        assertEquals(2, this.currencyRepository.findByDate(LocalDate.ofEpochDay(1L)).size());
    }

    /**
     * Method under test: {@link CurrencyRepository#findByBase(String)}
     */
    @Test
    void testFindByBase() {
        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        this.currencyRepository.save(currencyExchangeRateModelDto);
        this.currencyRepository.save(currencyExchangeRateModelDto1);
        assertTrue(this.currencyRepository.findByBase("foo").isEmpty());
    }
}

