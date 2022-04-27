package com.sda.currencyexchangeapp.service.exchange;

import static com.sda.currencyexchangeapp.service.exchange.GoldRatesService.modelMatcher;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sda.currencyexchangeapp.configuration.ConfigProperties;
import com.sda.currencyexchangeapp.model.exception.NoResultException;
import com.sda.currencyexchangeapp.model.gold.GoldExchangeRateModel;
import com.sda.currencyexchangeapp.model.gold.GoldExchangeRateModelDTO;
import com.sda.currencyexchangeapp.repository.GoldRepository;
import com.sda.currencyexchangeapp.service.API.APIConnectionService;
import com.sda.currencyexchangeapp.service.mapper.MapperToGoldDTO;
import com.sda.currencyexchangeapp.service.mapper.MapperToGoldModel;
import com.sda.currencyexchangeapp.service.validator.Validation;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;

@ContextConfiguration(classes = {GoldRatesService.class, ConfigProperties.class})
@ExtendWith(SpringExtension.class)
class GoldRatesServiceTest {
    @MockBean
    private APIConnectionService aPIConnectionService;

    @Autowired
    private GoldRatesService goldRatesService;

    @MockBean
    private GoldRepository goldRepository;

    @MockBean
    private MapperToGoldDTO mapperToGoldDTO;

    @MockBean
    private MapperToGoldModel mapperToGoldModel;

    @MockBean
    private Validation validation;

    @MockBean
    private GoldExchangeRateModelDTO goldExchangeRateModelDTO;

    @MockBean
    private GoldExchangeRateModel goldExchangeRateModel;

    @BeforeEach
    public void setUp(){
        goldExchangeRateModelDTO.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO.setId(123L);
        goldExchangeRateModelDTO.setPrice(10.0d);
    }


    @Test
    void testProcessCurrentGoldRateData() throws JsonProcessingException {
        when(this.mapperToGoldModel.mapJsonToModelObject()).thenReturn(new GoldExchangeRateModel());
        when(this.mapperToGoldDTO.convertGoldModelToDTO((GoldExchangeRateModel) any()))
                .thenReturn(goldExchangeRateModelDTO);
        when(this.goldRepository.exists(Example.of(goldExchangeRateModelDTO, modelMatcher)))
                .thenThrow(new NoResultException("An error occurred"));
        when(this.goldRepository.save((GoldExchangeRateModelDTO) any()))
                .thenThrow(new NoResultException("An error occurred"));
        assertThrows(NoResultException.class, () -> this.goldRatesService.processCurrentGoldRateData());
        verify(this.mapperToGoldModel).mapJsonToModelObject();
        verify(this.mapperToGoldDTO).convertGoldModelToDTO((GoldExchangeRateModel) any());
        verify(this.goldRepository).exists(Example.of(goldExchangeRateModelDTO, modelMatcher));
    }


    @Test
    void testProcessCurrentGoldRateData2() throws JsonProcessingException {
        when(this.mapperToGoldModel.mapJsonToModelObject()).thenReturn(new GoldExchangeRateModel());
        doNothing().when(goldExchangeRateModelDTO).setDate((LocalDate) any());
        doNothing().when(goldExchangeRateModelDTO).setId((Long) any());
        doNothing().when(goldExchangeRateModelDTO).setPrice((Double) any());
        when(this.mapperToGoldDTO.convertGoldModelToDTO((GoldExchangeRateModel) any()))
                .thenReturn(goldExchangeRateModelDTO);
        GoldExchangeRateModelDTO goldExchangeRateModelDTO1 = new GoldExchangeRateModelDTO();
        goldExchangeRateModelDTO1.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO1.setId(123L);
        goldExchangeRateModelDTO1.setPrice(10.0d);
        when(this.goldRepository.exists((Example.of(goldExchangeRateModelDTO, modelMatcher))))
                .thenReturn(true);
        when(this.goldRepository.save((GoldExchangeRateModelDTO) any())).thenReturn(goldExchangeRateModelDTO1);
        this.goldRatesService.processCurrentGoldRateData();
        verify(this.mapperToGoldModel).mapJsonToModelObject();
        verify(this.mapperToGoldDTO).convertGoldModelToDTO((GoldExchangeRateModel) any());
        verify(goldExchangeRateModelDTO).setDate((LocalDate) any());
        verify(goldExchangeRateModelDTO).setId((Long) any());
        verify(goldExchangeRateModelDTO).setPrice((Double) any());
        verify(this.goldRepository).exists((Example.of(goldExchangeRateModelDTO, modelMatcher)));
    }


    @Test
    void testProcessGoldRateDataForDate() throws JsonProcessingException {
        when(this.mapperToGoldModel.mapJsonToModelObject((String) any())).thenReturn(new GoldExchangeRateModel());
        when(this.mapperToGoldDTO.convertGoldModelToDTO((GoldExchangeRateModel) any()))
                .thenReturn(goldExchangeRateModelDTO);
        when(this.goldRepository.exists((Example.of(goldExchangeRateModelDTO, modelMatcher))))
                .thenThrow(new NoResultException("An error occurred"));
        when(this.goldRepository.save((GoldExchangeRateModelDTO) any()))
                .thenThrow(new NoResultException("An error occurred"));
        assertThrows(NoResultException.class, () -> this.goldRatesService.processGoldRateDataForDate("2020-03-01"));
        verify(this.mapperToGoldModel).mapJsonToModelObject((String) any());
        verify(this.mapperToGoldDTO).convertGoldModelToDTO((GoldExchangeRateModel) any());
        verify(this.goldRepository).exists((Example.of(goldExchangeRateModelDTO, modelMatcher)));
    }

    @Test
    void testGetCurrentGoldPrice() {
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        assertNull(this.goldRatesService.getCurrentGoldPrice());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    @Test
    void testGetCurrentGoldPrice3() {
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.CONTINUE));
        assertThrows(HttpClientErrorException.class, () -> this.goldRatesService.getCurrentGoldPrice());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    @Test
    void testGetGoldPriceForDate() {
        doNothing().when(this.validation).validateDateFormat((String[]) any());
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        assertNull(this.goldRatesService.getGoldPriceForDate("2020-03-01"));
        verify(this.validation).validateDateFormat((String[]) any());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    @Test
    void testGetGoldPriceForDate2() {
        doNothing().when(this.validation).validateDateFormat((String[]) any());
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.CONTINUE));
        assertThrows(NoResultException.class, () -> this.goldRatesService.getGoldPriceForDate("2020-03-01"));
        verify(this.validation).validateDateFormat((String[]) any());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    @Test
    void testGetGoldPriceForDate3() {
        doNothing().when(this.validation).validateDateFormat((String[]) any());
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenThrow(new NoResultException("An error occurred"));
        assertThrows(NoResultException.class, () -> this.goldRatesService.getGoldPriceForDate("2020-03-01"));
        verify(this.validation).validateDateFormat((String[]) any());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    @Test
    void testGetGoldPricesForPeriodOfTime() {
        doNothing().when(this.validation).validateDateFormat((String[]) any());
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        assertNull(this.goldRatesService.getGoldPricesForPeriodOfTime("2020-03-01", "2020-03-01"));
        verify(this.validation).validateDateFormat((String[]) any());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    @Test
    void testGetGoldPricesForPeriodOfTime2() {
        doNothing().when(this.validation).validateDateFormat((String[]) any());
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.CONTINUE));
        assertThrows(HttpClientErrorException.class,
                () -> this.goldRatesService.getGoldPricesForPeriodOfTime("2020-03-01", "2020-03-01"));
        verify(this.validation).validateDateFormat((String[]) any());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

}

