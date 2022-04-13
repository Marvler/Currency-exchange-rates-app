package com.sda.currencyexchangeapp.service;

import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModel;
import com.sda.currencyexchangeapp.model.CurrencyExchangeRateModelDto;
import org.springframework.stereotype.Component;

@Component
public class MapperToDTO {


    public CurrencyExchangeRateModelDto convertModelToDTO(CurrencyExchangeRateModel model){
        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase(model.getBase());
        currencyExchangeRateModelDto.setTarget(model.getRates().get(""););
        currencyExchangeRateModelDto.setBase(model.getBase());

    }
}
