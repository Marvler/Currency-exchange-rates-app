package com.sda.currencyexchangeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchangeRateModelDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String base;
    private String target;
    private LocalDate date;

    @Override
    public String toString() {
        return "CurrencyExchangeRateModelDto{" +
                "base='" + base + '\'' +
                ", target='" + target + '\'' +
                ", date=" + date +
                '}';
    }
}
