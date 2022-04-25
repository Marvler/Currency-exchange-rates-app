package com.sda.currencyexchangeapp.service.mapper;

import static org.junit.jupiter.api.Assertions.assertNull;

import com.sda.currencyexchangeapp.model.gold.GoldExchangeRateModel;
import com.sda.currencyexchangeapp.model.gold.GoldExchangeRateModelDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MapperToGoldDTO.class, GoldExchangeRateModel.class})
@ExtendWith(SpringExtension.class)
class MapperToGoldDTOTest {
    @Autowired
    private GoldExchangeRateModel goldExchangeRateModel;

    @Autowired
    private MapperToGoldDTO mapperToGoldDTO;

    /**
     * Method under test: {@link MapperToGoldDTO#convertGoldModelToDTO(GoldExchangeRateModel)}
     */
    @Test
    void testConvertGoldModelToDTO() {
        GoldExchangeRateModelDTO actualConvertGoldModelToDTOResult = this.mapperToGoldDTO
                .convertGoldModelToDTO(this.goldExchangeRateModel);
        assertNull(actualConvertGoldModelToDTOResult.getDate());
        assertNull(actualConvertGoldModelToDTOResult.getPrice());
    }
}

