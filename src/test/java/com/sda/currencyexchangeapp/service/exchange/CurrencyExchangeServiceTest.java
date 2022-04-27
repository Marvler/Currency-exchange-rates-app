package com.sda.currencyexchangeapp.service.exchange;

import static com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService.modelMatcher;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModel;
import com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModelDto;
import com.sda.currencyexchangeapp.model.exception.CurrencyProcessingException;
import com.sda.currencyexchangeapp.repository.CurrencyRepository;
import com.sda.currencyexchangeapp.service.mapper.MapperToCurrencyDTO;
import com.sda.currencyexchangeapp.service.mapper.MapperToCurrencyModel;
import com.sda.currencyexchangeapp.service.validator.Validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CurrencyExchangeService.class})
@ExtendWith(SpringExtension.class)
class CurrencyExchangeServiceTest {
    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @MockBean
    private CurrencyRepository currencyRepository;

    @MockBean
    private MapperToCurrencyDTO mapperToCurrencyDTO;

    @MockBean
    private MapperToCurrencyModel mapperToCurrencyModel;

    @MockBean
    private Validation validation;
    @MockBean
    private CurrencyExchangeRateModelDto currencyExchangeRateModelDto;
    @MockBean
    private CurrencyExchangeRateModel currencyExchangeRateModel;



    @BeforeEach
    public void setUp() {
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");


        currencyExchangeRateModel.setRates(new HashMap<>());
        currencyExchangeRateModel.setBase("Base");
        currencyExchangeRateModel.setDate(new Date(1 - 4 - 2021));
    }


    @Test
    void testGetAndProcessCurrencyExchangeRateAfterValidation1() throws JsonProcessingException {
        when(this.validation.validateIfCurrencyExists("USD", "PLN")).thenReturn(true);
        when(this.mapperToCurrencyModel.mapJsonToModelObject("USD", "PLN"))
                .thenReturn(currencyExchangeRateModel);
        when(this.mapperToCurrencyDTO.convertModelToDTO(currencyExchangeRateModel))
                .thenReturn(currencyExchangeRateModelDto);
        when(this.currencyRepository.exists((Example.of(currencyExchangeRateModelDto, modelMatcher))))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        when(this.currencyRepository.save(currencyExchangeRateModelDto))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        assertThrows(CurrencyProcessingException.class,
                () -> this.currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation("USD", "PLN"));
        verify(this.validation).validateIfCurrencyExists("USD", "PLN");
        verify(this.mapperToCurrencyModel).mapJsonToModelObject("USD", "PLN");
        verify(this.mapperToCurrencyDTO).convertModelToDTO(currencyExchangeRateModel);
        verify(this.currencyRepository)
                .exists(Example.of(currencyExchangeRateModelDto, modelMatcher));
    }


    @Test
    void testGetAndProcessCurrencyExchangeRateAfterValidation2() throws JsonProcessingException {
        when(this.validation.validateIfCurrencyExists((String) any(), (String) any())).thenReturn(false);
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(new CurrencyExchangeRateModel());

        when(this.mapperToCurrencyDTO.convertModelToDTO(currencyExchangeRateModel))
                .thenReturn(currencyExchangeRateModelDto);
        when(this.currencyRepository.exists((Example.of(currencyExchangeRateModelDto, modelMatcher))))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        when(this.currencyRepository.save(currencyExchangeRateModelDto))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        assertThrows(CurrencyProcessingException.class,
                () -> this.currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation("Base", "Target"));
        verify(this.validation).validateIfCurrencyExists((String) any(), (String) any());
    }


    @Test
    void testGetAndProcessCurrentCurrencyRateData() throws JsonProcessingException {
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(new CurrencyExchangeRateModel());
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);
        when(this.currencyRepository.exists((Example.of(currencyExchangeRateModelDto, modelMatcher))))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any()))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        assertThrows(CurrencyProcessingException.class,
                () -> this.currencyExchangeService.getAndProcessCurrentCurrencyRateData("Base", "Target"));
        verify(this.mapperToCurrencyModel).mapJsonToModelObject((String) any(), (String) any());
        verify(this.mapperToCurrencyDTO).convertModelToDTO((CurrencyExchangeRateModel) any());
        verify(this.currencyRepository)
                .exists(Example.of(currencyExchangeRateModelDto, modelMatcher));
    }

    @Test
    void testGetAllCurrenciesData() {
        ArrayList<CurrencyExchangeRateModelDto> currencyExchangeRateModelDtoList = new ArrayList<>();
        when(this.currencyRepository.findAll()).thenReturn(currencyExchangeRateModelDtoList);
        List<CurrencyExchangeRateModelDto> actualAllCurrenciesData = this.currencyExchangeService.getAllCurrenciesData();
        assertSame(currencyExchangeRateModelDtoList, actualAllCurrenciesData);
        assertTrue(actualAllCurrenciesData.isEmpty());
        verify(this.currencyRepository).findAll();
    }

    @Test
    void testGetAllCurrenciesData2() {
        when(this.currencyRepository.findAll()).thenThrow(new CurrencyProcessingException("An error occurred"));
        assertThrows(CurrencyProcessingException.class, () -> this.currencyExchangeService.getAllCurrenciesData());
        verify(this.currencyRepository).findAll();
    }

    @Test
    void testFindByBaseCurrency() {
        ArrayList<CurrencyExchangeRateModelDto> currencyExchangeRateModelDtoList = new ArrayList<>();
        when(this.currencyRepository.findByBase((String) any())).thenReturn(currencyExchangeRateModelDtoList);
        List<CurrencyExchangeRateModelDto> actualFindByBaseCurrencyResult = this.currencyExchangeService
                .findByBaseCurrency("GBP");
        assertSame(currencyExchangeRateModelDtoList, actualFindByBaseCurrencyResult);
        assertTrue(actualFindByBaseCurrencyResult.isEmpty());
        verify(this.currencyRepository).findByBase((String) any());
    }

    @Test
    void testFindByBaseCurrency2() {
        when(this.currencyRepository.findByBase((String) any()))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        assertThrows(CurrencyProcessingException.class, () -> this.currencyExchangeService.findByBaseCurrency("GBP"));
        verify(this.currencyRepository).findByBase((String) any());
    }
}