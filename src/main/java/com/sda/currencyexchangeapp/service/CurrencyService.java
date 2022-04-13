package com.sda.currencyexchangeapp.service;

import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModel;
import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModelDto;
import com.sda.currencyexchangeapp.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

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

    public CurrencyExchangeRateModelDto getAndProcessCurrency(String baseCurrency) {
       // CurrencyExchangeRateModel currency = //TODO currencyApiIntegrationService.get....
                CurrencyExchangeRateModelDto currencyDto = currencyMapper.map(currency) ;
        currencyRepository.save(currencyDto);
        return currencyDto;
    }

//    public WeatherDto getAndProcessWeather(String cityName) {
//        // Get weather from external service
//        Weather weather = weatherApiIntegrationService.getWeather(cityName);
//        // Save weather to database
//        weatherRepository.save(weather);
//        // Map weather to weatherDto
//        WeatherDto weatherDto = weatherMapper.map(weather);
//        // Return weatherDto to user
//        return weatherDto;
//    }
}
