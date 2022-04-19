package com.sda.currencyexchangeapp.rest;

import com.sda.currencyexchangeapp.service.GoldRatesService;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gold")
public class GoldRatesController {

    private final GoldRatesService goldRatesService;

    public GoldRatesController(GoldRatesService goldRatesService) {
        this.goldRatesService = goldRatesService;
    }

    @GetMapping("/current")
    public String getCurrentGoldRate() {
        return goldRatesService.getCurrentGoldPrice();
    }

//    @GetMapping("/{date}")
//    @ReadOperation
//    public String getCurrentGoldRateForDate(@PathVariable(name = "date") final String date) {
//        return goldRatesService.getGoldPriceForDate(date);
//    }

    @GetMapping("/date")
    public String getCurrentGoldRateForDate(@RequestParam String date) {
        return goldRatesService.getGoldPriceForDate(date);
    }


    @GetMapping("/period")
    public String getCurrentGoldRateForPeriod(@RequestParam final String startDate,
                             @RequestParam final String endDate ) {
        return goldRatesService.getGoldPricesForPeriodOfTime(startDate,endDate);
    }

}
