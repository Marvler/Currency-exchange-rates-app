package com.sda.currencyexchangeapp.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModelDto;
import com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/currency")
@RestController
public class ExchangeCurrencyController {

    private final CurrencyExchangeService currencyExchangeService;

    @Autowired
    public ExchangeCurrencyController(CurrencyExchangeService currencyExchangeService, CurrencyExchangeService currencyRepository) {
        this.currencyExchangeService = currencyExchangeService;
    }
    @GetMapping("/current")
    public String getCurrentCurrencyRate(@RequestParam String base, @RequestParam String target ) throws JsonProcessingException {
        return currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation(base.toUpperCase(),target.toUpperCase()).toString();
    }
    @GetMapping("/historical")
        public String getHistoricalCurrencyRate(@RequestParam String date, @RequestParam String base, @RequestParam String target) throws JsonProcessingException {
        return currencyExchangeService.getAndProcessCurrentCurrencyRateData(date,base.toUpperCase(), target.toUpperCase()).toString();
    }

    @GetMapping("/api/currencies/{base}")
    public List<CurrencyExchangeRateModelDto> getCurrencyRange(@PathVariable(name = "base") final String baseCurrency) {
        return currencyExchangeService.findByBaseCurrency(baseCurrency);
    }

    @GetMapping("/api/currencies/all")
    public List<CurrencyExchangeRateModelDto> getAllCurrencies(){
        return currencyExchangeService.getAllCurrenciesData();
    }

    @GetMapping("/count")
    public long count(){
        return currencyExchangeService.getDBcounter();
    }
}
