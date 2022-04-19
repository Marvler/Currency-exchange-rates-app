package com.sda.currencyexchangeapp.repository;

import com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModelDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyExchangeRateModelDto, Long>,
        CrudRepository<CurrencyExchangeRateModelDto, Long> {

    List<CurrencyExchangeRateModelDto> findByDate (LocalDate date);

    List<CurrencyExchangeRateModelDto> findByBase (String base);
}
