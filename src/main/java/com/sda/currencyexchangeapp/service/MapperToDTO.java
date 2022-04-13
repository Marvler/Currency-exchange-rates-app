package com.sda.currencyexchangeapp.service;

import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModel;
import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModelDto;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class MapperToDTO {


    public CurrencyExchangeRateModelDto convertModelToDTO(CurrencyExchangeRateModel model){
        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setTarget(model.getRates().keySet().stream().findFirst().get());
        currencyExchangeRateModelDto.setRate(model.getRates().values().stream().findFirst().get());
        currencyExchangeRateModelDto.setBase(model.getBase());
        currencyExchangeRateModelDto.setDate(model.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return currencyExchangeRateModelDto;

    }
}
