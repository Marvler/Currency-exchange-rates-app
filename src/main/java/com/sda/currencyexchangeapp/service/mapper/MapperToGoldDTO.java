package com.sda.currencyexchangeapp.service.mapper;

import com.sda.currencyexchangeapp.model.gold.GoldExchangeRateModel;
import com.sda.currencyexchangeapp.model.gold.GoldExchangeRateModelDTO;
import org.springframework.stereotype.Component;

@Component
public class MapperToGoldDTO {

    public GoldExchangeRateModelDTO convertGoldModelToDTO(GoldExchangeRateModel model){
        GoldExchangeRateModelDTO goldModelDTO = new GoldExchangeRateModelDTO();
        goldModelDTO.setPrice(model.getPrice());
        goldModelDTO.setDate(model.getDate());

        return goldModelDTO;
    }
}
