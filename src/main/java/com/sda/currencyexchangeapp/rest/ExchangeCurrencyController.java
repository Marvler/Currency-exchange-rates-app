package com.sda.currencyexchangeapp.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.currencyexchangeapp.service.CurrencyExchangeService;
import com.sda.currencyexchangeapp.service.MapperToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/currency")
@RestController
public class ExchangeCurrencyController {

    private final CurrencyExchangeService currencyExchangeService;
    private final MapperToModel mapper;


    @Autowired
    public ExchangeCurrencyController(CurrencyExchangeService currencyExchangeService, MapperToModel mapper) {
        this.currencyExchangeService = currencyExchangeService;
        this.mapper = mapper;
    }

    @GetMapping("/current")
    public String getCurrentCurrencyRate(@RequestParam String base, @RequestParam String target ) throws JsonProcessingException {
        return mapper.mapJsonToModelObject(base.toUpperCase(),target.toUpperCase()).toString();
    }

    @GetMapping("/historical")
        public String getHistoricalCurrencyRate(@RequestParam String date, @RequestParam String base, @RequestParam String target) throws JsonProcessingException {
        return mapper.mapJsonToModelObject(date, base.toUpperCase(), target.toUpperCase()).toString();
    }

}
