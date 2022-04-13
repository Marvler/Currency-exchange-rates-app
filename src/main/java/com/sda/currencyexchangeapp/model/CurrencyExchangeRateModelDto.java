package com.sda.currencyexchangeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.RoundingMode;
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
    private Double rate;

    @Override
    public String toString() {
        return "DTO MODEL\nId: " + id + "\nBase currency: " + base + "\nTarget currency: " + target
                +"\nDate: " + date + "\nRate: " + String.format("%.4f",1/rate);
    }
}

