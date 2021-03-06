package com.sda.currencyexchangeapp.service.exchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.currencyexchangeapp.configuration.ConfigProperties;
import com.sda.currencyexchangeapp.model.gold.GoldExchangeRateModel;
import com.sda.currencyexchangeapp.model.gold.GoldExchangeRateModelDTO;
import com.sda.currencyexchangeapp.model.exception.NoResultException;
import com.sda.currencyexchangeapp.repository.GoldRepository;
import com.sda.currencyexchangeapp.service.API.APIConnectionService;
import com.sda.currencyexchangeapp.service.mapper.MapperToGoldDTO;
import com.sda.currencyexchangeapp.service.mapper.MapperToGoldModel;
import com.sda.currencyexchangeapp.service.validator.Validation;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class GoldRatesService {

    private final APIConnectionService apiConnectionService;
    private final Validation validation;
    private final MapperToGoldDTO mapperToGoldDTO;
    private final MapperToGoldModel mapperToGoldModel;
    private final GoldRepository goldRepository;
    private final ConfigProperties configProperties;

    static ExampleMatcher modelMatcher = ExampleMatcher.matching()
            .withIgnorePaths("id");

    public GoldRatesService(APIConnectionService apiConnectionService, Validation validation, MapperToGoldDTO mapperToGoldDTO, MapperToGoldModel mapperToGoldModel, GoldRepository goldRepository, ConfigProperties configProperties) {
        this.apiConnectionService = apiConnectionService;
        this.validation = validation;
        this.mapperToGoldDTO = mapperToGoldDTO;
        this.mapperToGoldModel = mapperToGoldModel;
        this.goldRepository = goldRepository;
        this.configProperties = configProperties;
    }

    public String processCurrentGoldRateData() throws JsonProcessingException {
        GoldExchangeRateModel goldExchangeRateModel = mapperToGoldModel.mapJsonToModelObject();
        return dataBaseSaveValidation(goldExchangeRateModel);
    }

    public String processGoldRateDataForDate(String date) throws JsonProcessingException {
        GoldExchangeRateModel goldExchangeRateModel = mapperToGoldModel.mapJsonToModelObject(date);
        return dataBaseSaveValidation(goldExchangeRateModel);
    }

    public String getCurrentGoldPrice() {
        ResponseEntity<String> response = apiConnectionService.createApiConnection(configProperties.getGoldUrl());
        return response.getBody();
    }

    public String getGoldPriceForDate(final String date) {
        validation.validateDateFormat(date);
        try {
            ResponseEntity<String> response = apiConnectionService.createApiConnection(configProperties.getGoldUrl() + date);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new NoResultException("There was no gold record for date:" + date);
        }
    }

    public String getGoldPricesForPeriodOfTime(final String startDate, final String endDate) {
        validation.validateDateFormat(startDate, endDate);
        ResponseEntity<String> response = apiConnectionService.createApiConnection(configProperties.getGoldUrl() + startDate + "/" + endDate);
        return response.getBody();
    }

    private String dataBaseSaveValidation(GoldExchangeRateModel goldExchangeRateModel) {
        GoldExchangeRateModelDTO goldExchangeRateModelDTO = mapperToGoldDTO.convertGoldModelToDTO(goldExchangeRateModel);
        Example<GoldExchangeRateModelDTO> dtoExample = Example.of(goldExchangeRateModelDTO, modelMatcher);
        if (!goldRepository.exists(dtoExample)) {
            goldRepository.save(goldExchangeRateModelDTO);
        }
        return goldExchangeRateModelDTO.toString();
    }
}
