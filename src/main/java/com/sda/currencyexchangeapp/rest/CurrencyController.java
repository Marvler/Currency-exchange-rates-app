package com.sda.currencyexchangeapp.rest;

import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModel;
import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModelDto;
import com.sda.currencyexchangeapp.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    //TODO: dostosuj po integracji z api

    @GetMapping("/api/currencies/{base}")
        public List<CurrencyExchangeRateModel> getCurrencyRange(@PathVariable(name = "base") final String baseCurrency) {
        return currencyService.findByBaseCurrency(baseCurrency);
    }

    @GetMapping("/api/test")
    public void test(){
        currencyService.saveCurrencyExchangeRate(new CurrencyExchangeRateModelDto(1L,"USD","PLN", LocalDate.now()));
    }

//    @GetMapping("/api/weather/{cityName}")
//    public WeatherDto getWeather(@PathVariable(name = "cityName") final String cityName) {
//        return weatherService.getAndProcessWeather(cityName);
//    }
}
