package com.sda.currencyexchangeapp.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.currencyexchangeapp.configuration.ConfigProperties;
import com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModel;
import com.sda.currencyexchangeapp.service.API.APIConnectionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class MapperToCurrencyModel {

    private final APIConnectionService apiConnectionService;
    private final ObjectMapper objectMapper;
    private final ConfigProperties configProperties;

    @Autowired
    public MapperToCurrencyModel(APIConnectionService apiConnectionService, ObjectMapper objectMapper, ConfigProperties configProperties) {
        this.apiConnectionService = apiConnectionService;
        this.objectMapper = objectMapper;
        this.configProperties = configProperties;
    }

    public CurrencyExchangeRateModel mapJsonToModelObject(String base, String target) throws JsonProcessingException {
        return objectMapper.readValue(apiConnectionService.createApiConnection(String.format(configProperties.getCurrencyUrl(),base,target)).getBody(),CurrencyExchangeRateModel.class);
    }

    public CurrencyExchangeRateModel mapJsonToModelObject(String date, String base, String target) throws JsonProcessingException {
        return objectMapper.readValue(apiConnectionService.createApiConnection(String.format(configProperties.getCurrencyWithDateUrl(),date,base,target)).getBody(),CurrencyExchangeRateModel.class);
    }




}
