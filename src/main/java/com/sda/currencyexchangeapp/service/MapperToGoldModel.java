package com.sda.currencyexchangeapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.currencyexchangeapp.model.GoldExchangeRateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperToGoldModel {
    private final ObjectMapper objectMapper;
    private final ApiConnectionService apiConnectionService;

    @Autowired
    public MapperToGoldModel(ObjectMapper objectMapper, ApiConnectionService apiConnectionService) {
        this.objectMapper = objectMapper;
        this.apiConnectionService = apiConnectionService;
    }

    public GoldExchangeRateModel mapJsonToModelObject() throws JsonProcessingException {
        return objectMapper.readValue(apiConnectionService.createApiConnection("http://api.nbp.pl/api/cenyzlota/").getBody(),GoldExchangeRateModel.class);
//        return objectMapper.readValue(goldRatesService.getCurrentGoldPrice(), GoldExchangeRateModel.class);
    }

    public GoldExchangeRateModel mapJsonToModelObject(String date) throws JsonProcessingException {
        return objectMapper.readValue(apiConnectionService.createApiConnection("http://api.nbp.pl/api/cenyzlota/" + date).getBody(),GoldExchangeRateModel.class);
//        return objectMapper.readValue(goldRatesService.getGoldPriceForDate(date), GoldExchangeRateModel.class);
    }
}
