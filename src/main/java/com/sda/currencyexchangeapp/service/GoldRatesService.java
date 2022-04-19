package com.sda.currencyexchangeapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.currencyexchangeapp.model.GoldExchangeRateModel;
import com.sda.currencyexchangeapp.model.GoldExchangeRateModelDTO;
import com.sda.currencyexchangeapp.repository.GoldRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class GoldRatesService {

    private final ApiConnectionService apiConnectionService;
    private final String url = "http://api.nbp.pl/api/cenyzlota/";
    private final Validation validation;
    private final MapperToGoldDTO mapperToGoldDTO;
    private final MapperToGoldModel mapperToGoldModel;
    private final GoldRepository goldRepository;

    static ExampleMatcher modelMatcher = ExampleMatcher.matching()
            .withIgnorePaths("id");

    public GoldRatesService(ApiConnectionService apiConnectionService, Validation validation, MapperToGoldDTO mapperToGoldDTO, MapperToGoldModel mapperToGoldModel, GoldRepository goldRepository) {
        this.apiConnectionService = apiConnectionService;
        this.validation = validation;
        this.mapperToGoldDTO = mapperToGoldDTO;
        this.mapperToGoldModel = mapperToGoldModel;
        this.goldRepository = goldRepository;
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

    private String dataBaseSaveValidation(GoldExchangeRateModel goldExchangeRateModel) {
        GoldExchangeRateModelDTO goldExchangeRateModelDTO = mapperToGoldDTO.convertGoldModelToDTO(goldExchangeRateModel);
        Example<GoldExchangeRateModelDTO> dtoExample = Example.of(goldExchangeRateModelDTO, modelMatcher);
        if (!goldRepository.exists(dtoExample)) {
            goldRepository.save(goldExchangeRateModelDTO);
        }
        return goldExchangeRateModelDTO.toString();
    }
}
