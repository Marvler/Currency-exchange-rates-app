package com.sda.currencyexchangeapp.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModel;
import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModelDto;
import com.sda.currencyexchangeapp.service.CurrencyExchangeService;
import com.sda.currencyexchangeapp.service.MapperToDTO;
import com.sda.currencyexchangeapp.service.MapperToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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
        return currencyExchangeService.getAndProcessCurrentCurrencyRateData(base.toUpperCase(),target.toUpperCase()).toString();
    }

    @GetMapping("/historical")
        public String getHistoricalCurrencyRate(@RequestParam String date, @RequestParam String base, @RequestParam String target) throws JsonProcessingException {
        return currencyExchangeService.getAndProcessCurrentCurrencyRateData(date,base.toUpperCase(), target.toUpperCase()).toString();
    }
//
//    @GetMapping("/convert")
//    public String checkConvertModelToDTO(){
//        currencyExchangeRateModel.setBase("USD");
//        HashMap<String,Double> map= new HashMap<>();
//        map.put("PLN",4.28);
//        currencyExchangeRateModel.setRates(map);
//        currencyExchangeRateModel.setDate(new Date());
//        return mapperToDTO.convertModelToDTO(currencyExchangeRateModel).toString();
//    }

    @GetMapping("/api/currencies/{base}")
    public List<CurrencyExchangeRateModel> getCurrencyRange(@PathVariable(name = "base") final String baseCurrency) {
        return currencyExchangeService.findByBaseCurrency(baseCurrency);
    }

    @GetMapping("/api/test")
    public void test(){
        currencyExchangeService.saveCurrencyExchangeRate(new CurrencyExchangeRateModelDto(1L,"USD","PLN", LocalDate.now()));
    }

}
