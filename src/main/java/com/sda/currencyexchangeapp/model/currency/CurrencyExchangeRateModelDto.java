package com.sda.currencyexchangeapp.model.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Currencies_Rates")
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
        return "Base currency: " + base + "\nTarget currency: " + target
                +"\nDate: " + date + "\nRate: " + String.format("%.4f",rate);
    }
}
