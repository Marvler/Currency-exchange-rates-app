package com.sda.currencyexchangeapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.currencyexchangeapp.model.GoldProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class GoldRatesService {

    private final ApiConnectionService apiConnectionService;
    private final String url = "http://api.nbp.pl/api/cenyzlota/";
    private final Validation validation;

    public GoldRatesService(ApiConnectionService apiConnectionService, ObjectMapper objectMapper, Validation validation) {
        this.apiConnectionService = apiConnectionService;
        this.validation = validation;
    }

    public String getCurrentGoldPrice() {
        ResponseEntity<String> response = apiConnectionService.createApiConnection(url);
        return response.getBody();
    }

    public String getGoldPriceForDate(final String date) {
        validation.validateDateFormat(date);
        try {
            ResponseEntity<String> response = apiConnectionService.createApiConnection(url + date);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            return "There was no gold record for date: " + date;
        }
    }

    public String getGoldPricesForPeriodOfTime(final String startDate, final String endDate) {
        validation.validateDateFormat(startDate, endDate);
        ResponseEntity<String> response = apiConnectionService.createApiConnection(url + startDate + "/" + endDate);
        return response.getBody();
    }
}
