package com.sda.currencyexchangeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyExchangeRateModel implements Serializable {

    private String base;
    private HashMap<String, Double> rates;
    private Date date;


    @Override
    public String toString() {
        return "CurrencyExchangeRateModel{" +
                ", base='" + base + '\'' +
                ", target='" + rates.keySet() + '\'' +
                ", rates=" + rates.values() +
                ", date=" + date +
                '}';
    }
}
