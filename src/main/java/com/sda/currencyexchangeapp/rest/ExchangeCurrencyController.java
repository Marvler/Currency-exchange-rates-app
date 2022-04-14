package com.sda.currencyexchangeapp.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModel;
import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModelDto;
import com.sda.currencyexchangeapp.service.CurrencyExchangeService;
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
// example path: http://localhost:8080/currency/current?base=USD&target=PLN
    @GetMapping("/current")
    public String getCurrentCurrencyRate(@RequestParam String base, @RequestParam String target ) throws JsonProcessingException {
        return currencyExchangeService.getAndProcessCurrentCurrencyRateData(base.toUpperCase(),target.toUpperCase()).toString();
    }
// example path: http://localhost:8080/currency/historical?date=2010-05-04&base=PLN&target=EUR
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

    // example path: http://localhost:8080/currency/api/currencies/PLN
    @GetMapping("/api/currencies/{base}")
    public List<CurrencyExchangeRateModelDto> getCurrencyRange(@PathVariable(name = "base") final String baseCurrency) {
        return currencyExchangeService.findByBaseCurrency(baseCurrency);
    }

    // example path: http://localhost:8080/currency/api/currencies/all
    @GetMapping("/api/currencies/all")
    public List<CurrencyExchangeRateModelDto> getAllCurrencies(){
        return currencyExchangeService.getAllCurrenciesData();
    }

}
