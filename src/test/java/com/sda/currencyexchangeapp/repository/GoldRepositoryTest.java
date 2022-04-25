package com.sda.currencyexchangeapp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sda.currencyexchangeapp.model.gold.GoldExchangeRateModelDTO;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class GoldRepositoryTest {
    @Autowired
    private GoldRepository goldRepository;

    /**
     * Method under test: {@link GoldRepository#findByDate(LocalDate)}
     */
    @Test
    void testFindByDate() {
        GoldExchangeRateModelDTO goldExchangeRateModelDTO = new GoldExchangeRateModelDTO();
        goldExchangeRateModelDTO.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO.setPrice(10.0d);

        GoldExchangeRateModelDTO goldExchangeRateModelDTO1 = new GoldExchangeRateModelDTO();
        goldExchangeRateModelDTO1.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO1.setPrice(10.0d);
        this.goldRepository.save(goldExchangeRateModelDTO);
        this.goldRepository.save(goldExchangeRateModelDTO1);
        assertEquals(2, this.goldRepository.findByDate(LocalDate.ofEpochDay(1L)).size());
    }
}

