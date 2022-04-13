package com.sda.currencyexchangeapp.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class CurrencyExchangeService {

    private ApiConnectionService apiConnectionService;

    @Autowired
    public CurrencyExchangeService(ApiConnectionService apiConnectionService) {
        this.apiConnectionService = apiConnectionService;
    }


    public String getCurrentCurrencyRateData(String base, String target) {
        String url = String.format("https://api.exchangerate.host/latest?base=%s&symbols=%s",base,target);
        ResponseEntity<String> response = apiConnectionService.createApiConnection(url);
        return response.getBody();


    }




}
