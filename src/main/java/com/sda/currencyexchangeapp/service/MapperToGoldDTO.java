package com.sda.currencyexchangeapp.service;

import com.sda.currencyexchangeapp.model.GoldExchangeRateModel;
import com.sda.currencyexchangeapp.model.GoldExchangeRateModelDTO;
import org.springframework.stereotype.Component;

@Component
public class MapperToGoldDTO {

    public GoldExchangeRateModelDTO convertGoldModelToDTO(GoldExchangeRateModel model){
        GoldExchangeRateModelDTO goldModelDTO = new GoldExchangeRateModelDTO();
        goldModelDTO.setRate(model.getRate());
        goldModelDTO.setDate(model.getDate());
        return goldModelDTO;
    }
}
