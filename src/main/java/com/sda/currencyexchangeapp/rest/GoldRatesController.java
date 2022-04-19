package com.sda.currencyexchangeapp.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.currencyexchangeapp.service.exchange.GoldRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gold")
public class GoldRatesController {

    private final GoldRatesService goldRatesService;

    @Autowired
    public GoldRatesController(GoldRatesService goldRatesService ) {
        this.goldRatesService = goldRatesService;
    }

    @GetMapping("/current")
    public String getCurrentGoldRate() throws JsonProcessingException {
        goldRatesService.processCurrentGoldRateData();
        return goldRatesService.getCurrentGoldPrice();
    }

    @GetMapping("/date")
    public String getCurrentGoldRateForDate(@RequestParam String date) throws JsonProcessingException {
        goldRatesService.processGoldRateDataForDate(date);
        return goldRatesService.getGoldPriceForDate(date);
    }

    @GetMapping("/period")
    public String getCurrentGoldRateForPeriod(@RequestParam final String startDate,
                                              @RequestParam final String endDate) {
        return goldRatesService.getGoldPricesForPeriodOfTime(startDate, endDate);
    }

}
