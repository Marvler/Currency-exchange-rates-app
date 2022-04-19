package com.sda.currencyexchangeapp.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.currencyexchangeapp.model.gold.GoldExchangeRateModel;
import com.sda.currencyexchangeapp.service.API.APIConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperToGoldModel {
    private final ObjectMapper objectMapper;
    private final APIConnectionService apiConnectionService;

    @Autowired
    public MapperToGoldModel(ObjectMapper objectMapper, APIConnectionService apiConnectionService) {
        this.objectMapper = objectMapper;
        this.apiConnectionService = apiConnectionService;
    }

    public GoldExchangeRateModel mapJsonToModelObject() throws JsonProcessingException {
        return objectMapper.readValue(getFormattedBody(""), GoldExchangeRateModel.class);
    }

    public GoldExchangeRateModel mapJsonToModelObject(String date) throws JsonProcessingException {
        return objectMapper.readValue(getFormattedBody(date), GoldExchangeRateModel.class);
    }

    private String getFormattedBody(String date) {
        String body = apiConnectionService.createApiConnection("http://api.nbp.pl/api/cenyzlota/" + date).getBody().replace("data", "date").replace("cena", "price");
        return body.substring(1, body.length() - 1);
    }
}
