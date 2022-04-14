package com.sda.currencyexchangeapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class CurrencyExchangeRateModel implements Serializable {

    private String base;
    private HashMap<String, Double> rates;
    private Date date;


    @Override
    public String toString() {
        return "Base currency: " + base + "\nTarget currency: " + rates.keySet().stream().findFirst().get()
                + "\nRate: " + String.format("%.4f", 1/rates.values().stream().findFirst().get()) +
                "\nDate: " + date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    }
}
