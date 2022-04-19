package com.sda.currencyexchangeapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModel;
import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModelDto;
import com.sda.currencyexchangeapp.model.CurrencyProcessingException;
import com.sda.currencyexchangeapp.repository.CurrencyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@Service
public class CurrencyExchangeService {

    private final MapperToModel mapperToModel;
    private final CurrencyRepository currencyRepository;
    private final MapperToDTO mapperToDTO;
    private final Validation validation;

    static ExampleMatcher modelMatcher = ExampleMatcher.matching()
            .withIgnorePaths("id");

    public CurrencyExchangeService(MapperToModel mapperToModel, CurrencyRepository currencyRepository, MapperToDTO mapperToDTO, Validation validation) {
        this.mapperToModel = mapperToModel;
        this.currencyRepository = currencyRepository;
        this.mapperToDTO = mapperToDTO;
        this.validation = validation;
    }

    public String getAndProcessCurrencyExchangeRateAfterValidation(String base, String target) throws JsonProcessingException {

        if (base.equalsIgnoreCase(target)) {
            throw new CurrencyProcessingException("Base currency and Target currency is the same. Guess the result :). DB not updated.");
        } else if (!validation.validateIfCurrencyExists(base, target)) {
            throw new CurrencyProcessingException("Cannot get currency exchange data. DB not updated");
        } else {
            return getAndProcessCurrentCurrencyRateData(base, target);
        }
    }

    public String getAndProcessCurrentCurrencyRateData(String base, String target) throws JsonProcessingException {


        CurrencyExchangeRateModel currencyExchangeRateModel = mapperToModel.mapJsonToModelObject(base, target);
        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = mapperToDTO.convertModelToDTO(currencyExchangeRateModel);
        Example<CurrencyExchangeRateModelDto> dtoExample = Example.of(currencyExchangeRateModelDto, modelMatcher);
        if (!currencyRepository.exists(dtoExample)) {
            currencyRepository.save(currencyExchangeRateModelDto);
        }

        return currencyExchangeRateModel.toString();
    }

    public String getAndProcessCurrentCurrencyRateData(String date, String base, String target) throws JsonProcessingException {

        CurrencyExchangeRateModel currencyExchangeRateModel = mapperToModel.mapJsonToModelObject(date, base, target);
        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = mapperToDTO.convertModelToDTO(currencyExchangeRateModel);
        Example<CurrencyExchangeRateModelDto> dtoExample = Example.of(currencyExchangeRateModelDto, modelMatcher);
        if (!currencyRepository.exists(dtoExample)) {
            currencyRepository.save(currencyExchangeRateModelDto);
        }
        return currencyExchangeRateModelDto.toString();
    }

    public List<CurrencyExchangeRateModelDto> getAllCurrenciesData() {
        return currencyRepository.findAll();
    }

    public List<CurrencyExchangeRateModelDto> findByDate(CurrencyExchangeRateModel currency, LocalDate date) {
        return currencyRepository.findByDate(date);
    }

    public List<CurrencyExchangeRateModelDto> findByBaseCurrency(String baseCurrency) {
        return currencyRepository.findByBase(baseCurrency);
    }

}