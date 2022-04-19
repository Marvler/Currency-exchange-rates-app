package com.sda.currencyexchangeapp.service.exchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModel;
import com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModelDto;
import com.sda.currencyexchangeapp.model.gold.CurrencyProcessingException;
import com.sda.currencyexchangeapp.repository.CurrencyRepository;
import com.sda.currencyexchangeapp.service.mapper.MapperToCurrencyDTO;
import com.sda.currencyexchangeapp.service.mapper.MapperToCurrencyModel;
import com.sda.currencyexchangeapp.service.validator.Validation;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@Service
public class CurrencyExchangeService {

    private final MapperToCurrencyModel mapperToCurrencyModel;
    private final CurrencyRepository currencyRepository;
    private final MapperToCurrencyDTO mapperToDTO;
    private final Validation validation;

    static ExampleMatcher modelMatcher = ExampleMatcher.matching()
            .withIgnorePaths("id");

    public CurrencyExchangeService(MapperToCurrencyModel mapperToCurrencyModel, CurrencyRepository currencyRepository, MapperToCurrencyDTO mapperToDTO, Validation validation) {
        this.mapperToCurrencyModel = mapperToCurrencyModel;
        this.currencyRepository = currencyRepository;
        this.mapperToDTO = mapperToDTO;
        this.validation = validation;
    }

    public String getAndProcessCurrencyExchangeRateAfterValidation(String base, String target) throws JsonProcessingException {

        if (base.equalsIgnoreCase(target)) {
            throw new CurrencyProcessingException("Base currency and Target currency are the same. Guess the result :). DB not updated.");
        } else if (!validation.validateIfCurrencyExists(base, target)) {
            throw new CurrencyProcessingException("Cannot get currency exchange data. DB not updated");
        } else {
            return getAndProcessCurrentCurrencyRateData(base, target);
        }
    }

    public String getAndProcessCurrentCurrencyRateData(String base, String target) throws JsonProcessingException {


        CurrencyExchangeRateModel currencyExchangeRateModel = mapperToCurrencyModel.mapJsonToModelObject(base, target);
        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = mapperToDTO.convertModelToDTO(currencyExchangeRateModel);
        Example<CurrencyExchangeRateModelDto> dtoExample = Example.of(currencyExchangeRateModelDto, modelMatcher);
        if (!currencyRepository.exists(dtoExample)) {
            currencyRepository.save(currencyExchangeRateModelDto);
        }

        return currencyExchangeRateModel.toString();
    }

    public String getAndProcessCurrentCurrencyRateData(String date, String base, String target) throws JsonProcessingException {

        CurrencyExchangeRateModel currencyExchangeRateModel = mapperToCurrencyModel.mapJsonToModelObject(date, base, target);
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

    public long getDBcounter(){
        return currencyRepository.count();
    }

}