package com.sda.currencyexchangeapp.rest;

import com.sda.currencyexchangeapp.service.APICountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/count")
public class ApiCountController {

    private final APICountService apiCountService;

    public ApiCountController(APICountService apiCountService) {
        this.apiCountService = apiCountService;
    }

    @GetMapping("/gold/current")
    public String getNumberOfApiCallsForCurrentGold() {
        return "Number of API calls for getting current date of Gold rate: " + apiCountService.getTotalNumberOfApiCalls("/gold/current");
    }

    @GetMapping("/gold/dates")
    public String getNumberOfApiCallsForGoldInPeriodOfTime(){
        int totalNumberOfAPICallsForDates = (int)(Double.parseDouble(apiCountService.getTotalNumberOfApiCalls("/gold/date"))
                + Double.parseDouble(apiCountService.getTotalNumberOfApiCalls("/gold/period")));
        return "Number of API calls for getting data of Gold price in period of time: " + totalNumberOfAPICallsForDates;
    }

    @GetMapping("/currencies/current")
    public String getNumberOfApiCallsForCurrentCurrenciesData(){
        return "Number of API calls for getting current data regarding currencies: " + (int)Double.parseDouble(apiCountService.getTotalNumberOfApiCalls("/currency/current"));
    }

    @GetMapping("/currencies/period")
    public String getNumberOfApiCallsForCurrenciesInPeriodOfTime(){
        int totalNumberOfAPICallsForDates = (int)(Double.parseDouble(apiCountService.getTotalNumberOfApiCalls("/currency/historical")));
        return "Number of API calls for getting data of Gold price in period of time: " +  totalNumberOfAPICallsForDates;
    }



}
