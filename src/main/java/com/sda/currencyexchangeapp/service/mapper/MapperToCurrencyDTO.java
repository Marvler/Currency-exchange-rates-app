package com.sda.currencyexchangeapp.service.mapper;

import com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModel;
import com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModelDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;

@Component
public class MapperToCurrencyDTO {


    public CurrencyExchangeRateModelDto convertModelToDTO(CurrencyExchangeRateModel model){
        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setTarget(model.getRates().keySet().stream().findFirst().get());
        currencyExchangeRateModelDto.setRate(BigDecimal.valueOf(model.getRates().values().stream().findFirst().get()).setScale(4, RoundingMode.HALF_EVEN).doubleValue());
        currencyExchangeRateModelDto.setBase(model.getBase());
        currencyExchangeRateModelDto.setDate(model.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return currencyExchangeRateModelDto;

    }
}
