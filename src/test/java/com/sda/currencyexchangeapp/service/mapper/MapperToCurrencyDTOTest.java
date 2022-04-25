package com.sda.currencyexchangeapp.service.mapper;

import com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MapperToCurrencyDTO.class, CurrencyExchangeRateModel.class})
@ExtendWith(SpringExtension.class)
class MapperToCurrencyDTOTest {
    @Autowired
    private CurrencyExchangeRateModel currencyExchangeRateModel;

    @Autowired
    private MapperToCurrencyDTO mapperToCurrencyDTO;

    /**
     * Method under test: {@link MapperToCurrencyDTO#convertModelToDTO(CurrencyExchangeRateModel)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConvertModelToDTO() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Map.keySet()" because "that" is null
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToCurrencyDTO.convertModelToDTO(MapperToCurrencyDTO.java:17)
        //   In order to prevent convertModelToDTO(CurrencyExchangeRateModel)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   convertModelToDTO(CurrencyExchangeRateModel).
        //   See https://diff.blue/R013 to resolve this issue.

        this.mapperToCurrencyDTO.convertModelToDTO(this.currencyExchangeRateModel);
    }
}

