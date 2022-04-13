package com.sda.currencyexchangeapp.repository;

import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModel;
import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModelDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyExchangeRateModelDto, Long> {

    List<CurrencyExchangeRateModel> findByDate (LocalDate date);

    List<CurrencyExchangeRateModel> findByBase (String baseCurrency);
}
