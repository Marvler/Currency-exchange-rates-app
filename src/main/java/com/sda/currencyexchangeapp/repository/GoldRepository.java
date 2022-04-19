package com.sda.currencyexchangeapp.repository;

import com.sda.currencyexchangeapp.model.gold.GoldExchangeRateModelDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GoldRepository extends JpaRepository<GoldExchangeRateModelDTO, Long> {

    List<GoldExchangeRateModelDTO> findByDate (LocalDate data);
}
