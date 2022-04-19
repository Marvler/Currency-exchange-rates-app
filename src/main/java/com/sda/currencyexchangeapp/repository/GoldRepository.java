package com.sda.currencyexchangeapp.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface GoldRepository extends JpaRepository<GoldExchangeRateModelDto, Long> {

    List<GoldExchangeRateModelDto> findByDate (LocalDate date);

    List<GoldExchangeRateModelDto> findByPeriod (LocalDate period);

}
