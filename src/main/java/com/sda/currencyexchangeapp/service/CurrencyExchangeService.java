package com.sda.currencyexchangeapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModel;
import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModelDto;
import com.sda.currencyexchangeapp.repository.CurrencyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Log4j2
@Service
public class CurrencyExchangeService {

    private ApiConnectionService apiConnectionService;
    private MapperToModel mapperToModel;

    @Autowired
    public CurrencyExchangeService(ApiConnectionService apiConnectionService, MapperToModel mapperToModel) {
        this.apiConnectionService = apiConnectionService;
        this.mapperToModel = mapperToModel;
    }


    public String getAndProcessCurrentCurrencyRateData(String base, String target) throws JsonProcessingException {
        //Get Data From API
        CurrencyExchangeRateModel currencyExchangeRateModel = mapperToModel.mapJsonToModelObject(base, target);
        //TODO Check if in DB and save if not

//        String url = String.format("https://api.exchangerate.host/latest?base=%s&symbols=%s",base,target);
//        ResponseEntity<String> response = apiConnectionService.createApiConnection(url);
//        return response.getBody();
        return currencyExchangeRateModel.toString();
    }
    public String getAndProcessCurrentCurrencyRateData(String date, String base, String target) throws JsonProcessingException {
        //Get Data From API
        CurrencyExchangeRateModel currencyExchangeRateModel = mapperToModel.mapJsonToModelObject(date,base, target);
        //TODO Check if in DB and save if not

    }


        return currencyExchangeRateModel.toString();

    public void saveCurrencyExchangeRate(CurrencyExchangeRateModelDto currencyExchangeRateModelDto){
        currencyRepository.save(currencyExchangeRateModelDto);

    }

    public List<CurrencyExchangeRateModelDto> getAllCurrenciesData(){
        return currencyRepository.findAll();
    }

    public List<CurrencyExchangeRateModel> findByDate(CurrencyExchangeRateModel currency, LocalDate date) {
        return currencyRepository.findByDate(date);
    }


    public List<CurrencyExchangeRateModel> findByBaseCurrency(String baseCurrency) {
        return currencyRepository.findByBase(baseCurrency);
    }

//    public CurrencyExchangeRateModelDto getAndProcessCurrency() {
//        CurrencyExchangeRateModel currency = mapperToModel.mapJsonToModelObject(baseCurrency, )
//        CurrencyExchangeRateModelDto currencyDto = mapperToDTO.convertModelToDTO(currency) ;
//        currencyRepository.save(currencyDto);
//        return currencyDto;
//    }



}
