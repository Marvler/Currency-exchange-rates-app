package com.sda.currencyexchangeapp.service.exchange;

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

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    private ConfigProperties configProperties;

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

    /**
     * Method under test: {@link GoldRatesService#processCurrentGoldRateData()}
     */
    @Test
    void testProcessCurrentGoldRateData() throws JsonProcessingException {
        when(this.mapperToGoldModel.mapJsonToModelObject()).thenReturn(new GoldExchangeRateModel());

        GoldExchangeRateModelDTO goldExchangeRateModelDTO = new GoldExchangeRateModelDTO();
        goldExchangeRateModelDTO.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO.setId(123L);
        goldExchangeRateModelDTO.setPrice(10.0d);
        when(this.mapperToGoldDTO.convertGoldModelToDTO((GoldExchangeRateModel) any()))
                .thenReturn(goldExchangeRateModelDTO);

        GoldExchangeRateModelDTO goldExchangeRateModelDTO1 = new GoldExchangeRateModelDTO();
        goldExchangeRateModelDTO1.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO1.setId(123L);
        goldExchangeRateModelDTO1.setPrice(10.0d);
        when(this.goldRepository.exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any()))
                .thenReturn(true);
        when(this.goldRepository.save((GoldExchangeRateModelDTO) any())).thenReturn(goldExchangeRateModelDTO1);
        assertEquals("GoldExchangeRateModelDTO(id=123, price=10.0, date=1970-01-02)",
                this.goldRatesService.processCurrentGoldRateData());
        verify(this.mapperToGoldModel).mapJsonToModelObject();
        verify(this.mapperToGoldDTO).convertGoldModelToDTO((GoldExchangeRateModel) any());
        verify(this.goldRepository).exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any());
    }

    /**
     * Method under test: {@link GoldRatesService#processCurrentGoldRateData()}
     */
    @Test
    void testProcessCurrentGoldRateData2() throws JsonProcessingException {
        when(this.mapperToGoldModel.mapJsonToModelObject()).thenReturn(new GoldExchangeRateModel());

        GoldExchangeRateModelDTO goldExchangeRateModelDTO = new GoldExchangeRateModelDTO();
        goldExchangeRateModelDTO.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO.setId(123L);
        goldExchangeRateModelDTO.setPrice(10.0d);
        when(this.mapperToGoldDTO.convertGoldModelToDTO((GoldExchangeRateModel) any()))
                .thenReturn(goldExchangeRateModelDTO);
        when(this.goldRepository.exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any()))
                .thenThrow(new NoResultException("An error occurred"));
        when(this.goldRepository.save((GoldExchangeRateModelDTO) any()))
                .thenThrow(new NoResultException("An error occurred"));
        assertThrows(NoResultException.class, () -> this.goldRatesService.processCurrentGoldRateData());
        verify(this.mapperToGoldModel).mapJsonToModelObject();
        verify(this.mapperToGoldDTO).convertGoldModelToDTO((GoldExchangeRateModel) any());
        verify(this.goldRepository).exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any());
    }

    /**
     * Method under test: {@link GoldRatesService#processCurrentGoldRateData()}
     */
    @Test
    void testProcessCurrentGoldRateData3() throws JsonProcessingException {
        when(this.mapperToGoldModel.mapJsonToModelObject()).thenReturn(new GoldExchangeRateModel());
        GoldExchangeRateModelDTO goldExchangeRateModelDTO = mock(GoldExchangeRateModelDTO.class);
        doNothing().when(goldExchangeRateModelDTO).setDate((LocalDate) any());
        doNothing().when(goldExchangeRateModelDTO).setId((Long) any());
        doNothing().when(goldExchangeRateModelDTO).setPrice((Double) any());
        goldExchangeRateModelDTO.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO.setId(123L);
        goldExchangeRateModelDTO.setPrice(10.0d);
        when(this.mapperToGoldDTO.convertGoldModelToDTO((GoldExchangeRateModel) any()))
                .thenReturn(goldExchangeRateModelDTO);

        GoldExchangeRateModelDTO goldExchangeRateModelDTO1 = new GoldExchangeRateModelDTO();
        goldExchangeRateModelDTO1.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO1.setId(123L);
        goldExchangeRateModelDTO1.setPrice(10.0d);
        when(this.goldRepository.exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any()))
                .thenReturn(true);
        when(this.goldRepository.save((GoldExchangeRateModelDTO) any())).thenReturn(goldExchangeRateModelDTO1);
        this.goldRatesService.processCurrentGoldRateData();
        verify(this.mapperToGoldModel).mapJsonToModelObject();
        verify(this.mapperToGoldDTO).convertGoldModelToDTO((GoldExchangeRateModel) any());
        verify(goldExchangeRateModelDTO).setDate((LocalDate) any());
        verify(goldExchangeRateModelDTO).setId((Long) any());
        verify(goldExchangeRateModelDTO).setPrice((Double) any());
        verify(this.goldRepository).exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any());
    }

    /**
     * Method under test: {@link GoldRatesService#processCurrentGoldRateData()}
     */
    @Test
    void testProcessCurrentGoldRateData4() throws JsonProcessingException {
        when(this.mapperToGoldModel.mapJsonToModelObject()).thenReturn(new GoldExchangeRateModel());
        GoldExchangeRateModelDTO goldExchangeRateModelDTO = mock(GoldExchangeRateModelDTO.class);
        doNothing().when(goldExchangeRateModelDTO).setDate((LocalDate) any());
        doNothing().when(goldExchangeRateModelDTO).setId((Long) any());
        doNothing().when(goldExchangeRateModelDTO).setPrice((Double) any());
        goldExchangeRateModelDTO.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO.setId(123L);
        goldExchangeRateModelDTO.setPrice(10.0d);
        when(this.mapperToGoldDTO.convertGoldModelToDTO((GoldExchangeRateModel) any()))
                .thenReturn(goldExchangeRateModelDTO);

        GoldExchangeRateModelDTO goldExchangeRateModelDTO1 = new GoldExchangeRateModelDTO();
        goldExchangeRateModelDTO1.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO1.setId(123L);
        goldExchangeRateModelDTO1.setPrice(10.0d);
        when(this.goldRepository.exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any()))
                .thenReturn(false);
        when(this.goldRepository.save((GoldExchangeRateModelDTO) any())).thenReturn(goldExchangeRateModelDTO1);
        this.goldRatesService.processCurrentGoldRateData();
        verify(this.mapperToGoldModel).mapJsonToModelObject();
        verify(this.mapperToGoldDTO).convertGoldModelToDTO((GoldExchangeRateModel) any());
        verify(goldExchangeRateModelDTO).setDate((LocalDate) any());
        verify(goldExchangeRateModelDTO).setId((Long) any());
        verify(goldExchangeRateModelDTO).setPrice((Double) any());
        verify(this.goldRepository).exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any());
        verify(this.goldRepository).save((GoldExchangeRateModelDTO) any());
    }

    /**
     * Method under test: {@link GoldRatesService#processGoldRateDataForDate(String)}
     */
    @Test
    void testProcessGoldRateDataForDate() throws JsonProcessingException {
        when(this.mapperToGoldModel.mapJsonToModelObject((String) any())).thenReturn(new GoldExchangeRateModel());

        GoldExchangeRateModelDTO goldExchangeRateModelDTO = new GoldExchangeRateModelDTO();
        goldExchangeRateModelDTO.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO.setId(123L);
        goldExchangeRateModelDTO.setPrice(10.0d);
        when(this.mapperToGoldDTO.convertGoldModelToDTO((GoldExchangeRateModel) any()))
                .thenReturn(goldExchangeRateModelDTO);

        GoldExchangeRateModelDTO goldExchangeRateModelDTO1 = new GoldExchangeRateModelDTO();
        goldExchangeRateModelDTO1.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO1.setId(123L);
        goldExchangeRateModelDTO1.setPrice(10.0d);
        when(this.goldRepository.exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any()))
                .thenReturn(true);
        when(this.goldRepository.save((GoldExchangeRateModelDTO) any())).thenReturn(goldExchangeRateModelDTO1);
        assertEquals("GoldExchangeRateModelDTO(id=123, price=10.0, date=1970-01-02)",
                this.goldRatesService.processGoldRateDataForDate("2020-03-01"));
        verify(this.mapperToGoldModel).mapJsonToModelObject((String) any());
        verify(this.mapperToGoldDTO).convertGoldModelToDTO((GoldExchangeRateModel) any());
        verify(this.goldRepository).exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any());
    }

    /**
     * Method under test: {@link GoldRatesService#processGoldRateDataForDate(String)}
     */
    @Test
    void testProcessGoldRateDataForDate2() throws JsonProcessingException {
        when(this.mapperToGoldModel.mapJsonToModelObject((String) any())).thenReturn(new GoldExchangeRateModel());

        GoldExchangeRateModelDTO goldExchangeRateModelDTO = new GoldExchangeRateModelDTO();
        goldExchangeRateModelDTO.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO.setId(123L);
        goldExchangeRateModelDTO.setPrice(10.0d);
        when(this.mapperToGoldDTO.convertGoldModelToDTO((GoldExchangeRateModel) any()))
                .thenReturn(goldExchangeRateModelDTO);
        when(this.goldRepository.exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any()))
                .thenThrow(new NoResultException("An error occurred"));
        when(this.goldRepository.save((GoldExchangeRateModelDTO) any()))
                .thenThrow(new NoResultException("An error occurred"));
        assertThrows(NoResultException.class, () -> this.goldRatesService.processGoldRateDataForDate("2020-03-01"));
        verify(this.mapperToGoldModel).mapJsonToModelObject((String) any());
        verify(this.mapperToGoldDTO).convertGoldModelToDTO((GoldExchangeRateModel) any());
        verify(this.goldRepository).exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any());
    }

    /**
     * Method under test: {@link GoldRatesService#processGoldRateDataForDate(String)}
     */
    @Test
    void testProcessGoldRateDataForDate3() throws JsonProcessingException {
        when(this.mapperToGoldModel.mapJsonToModelObject((String) any())).thenReturn(new GoldExchangeRateModel());
        GoldExchangeRateModelDTO goldExchangeRateModelDTO = mock(GoldExchangeRateModelDTO.class);
        doNothing().when(goldExchangeRateModelDTO).setDate((LocalDate) any());
        doNothing().when(goldExchangeRateModelDTO).setId((Long) any());
        doNothing().when(goldExchangeRateModelDTO).setPrice((Double) any());
        goldExchangeRateModelDTO.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO.setId(123L);
        goldExchangeRateModelDTO.setPrice(10.0d);
        when(this.mapperToGoldDTO.convertGoldModelToDTO((GoldExchangeRateModel) any()))
                .thenReturn(goldExchangeRateModelDTO);

        GoldExchangeRateModelDTO goldExchangeRateModelDTO1 = new GoldExchangeRateModelDTO();
        goldExchangeRateModelDTO1.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO1.setId(123L);
        goldExchangeRateModelDTO1.setPrice(10.0d);
        when(this.goldRepository.exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any()))
                .thenReturn(true);
        when(this.goldRepository.save((GoldExchangeRateModelDTO) any())).thenReturn(goldExchangeRateModelDTO1);
        this.goldRatesService.processGoldRateDataForDate("2020-03-01");
        verify(this.mapperToGoldModel).mapJsonToModelObject((String) any());
        verify(this.mapperToGoldDTO).convertGoldModelToDTO((GoldExchangeRateModel) any());
        verify(goldExchangeRateModelDTO).setDate((LocalDate) any());
        verify(goldExchangeRateModelDTO).setId((Long) any());
        verify(goldExchangeRateModelDTO).setPrice((Double) any());
        verify(this.goldRepository).exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any());
    }

    /**
     * Method under test: {@link GoldRatesService#processGoldRateDataForDate(String)}
     */
    @Test
    void testProcessGoldRateDataForDate4() throws JsonProcessingException {
        when(this.mapperToGoldModel.mapJsonToModelObject((String) any())).thenReturn(new GoldExchangeRateModel());
        GoldExchangeRateModelDTO goldExchangeRateModelDTO = mock(GoldExchangeRateModelDTO.class);
        doNothing().when(goldExchangeRateModelDTO).setDate((LocalDate) any());
        doNothing().when(goldExchangeRateModelDTO).setId((Long) any());
        doNothing().when(goldExchangeRateModelDTO).setPrice((Double) any());
        goldExchangeRateModelDTO.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO.setId(123L);
        goldExchangeRateModelDTO.setPrice(10.0d);
        when(this.mapperToGoldDTO.convertGoldModelToDTO((GoldExchangeRateModel) any()))
                .thenReturn(goldExchangeRateModelDTO);

        GoldExchangeRateModelDTO goldExchangeRateModelDTO1 = new GoldExchangeRateModelDTO();
        goldExchangeRateModelDTO1.setDate(LocalDate.ofEpochDay(1L));
        goldExchangeRateModelDTO1.setId(123L);
        goldExchangeRateModelDTO1.setPrice(10.0d);
        when(this.goldRepository.exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any()))
                .thenReturn(false);
        when(this.goldRepository.save((GoldExchangeRateModelDTO) any())).thenReturn(goldExchangeRateModelDTO1);
        this.goldRatesService.processGoldRateDataForDate("2020-03-01");
        verify(this.mapperToGoldModel).mapJsonToModelObject((String) any());
        verify(this.mapperToGoldDTO).convertGoldModelToDTO((GoldExchangeRateModel) any());
        verify(goldExchangeRateModelDTO).setDate((LocalDate) any());
        verify(goldExchangeRateModelDTO).setId((Long) any());
        verify(goldExchangeRateModelDTO).setPrice((Double) any());
        verify(this.goldRepository).exists((org.springframework.data.domain.Example<GoldExchangeRateModelDTO>) any());
        verify(this.goldRepository).save((GoldExchangeRateModelDTO) any());
    }

    /**
     * Method under test: {@link GoldRatesService#getCurrentGoldPrice()}
     */
    @Test
    void testGetCurrentGoldPrice() {
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        assertNull(this.goldRatesService.getCurrentGoldPrice());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    /**
     * Method under test: {@link GoldRatesService#getCurrentGoldPrice()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCurrentGoldPrice2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.sda.currencyexchangeapp.service.exchange.GoldRatesService.getCurrentGoldPrice(GoldRatesService.java:53)
        //   In order to prevent getCurrentGoldPrice()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getCurrentGoldPrice().
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.aPIConnectionService.createApiConnection((String) any())).thenReturn(null);
        this.goldRatesService.getCurrentGoldPrice();
    }

    /**
     * Method under test: {@link GoldRatesService#getCurrentGoldPrice()}
     */
    @Test
    void testGetCurrentGoldPrice3() {
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.CONTINUE));
        assertThrows(HttpClientErrorException.class, () -> this.goldRatesService.getCurrentGoldPrice());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    /**
     * Method under test: {@link GoldRatesService#getGoldPriceForDate(String)}
     */
    @Test
    void testGetGoldPriceForDate() {
        doNothing().when(this.validation).validateDateFormat((String[]) any());
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        assertNull(this.goldRatesService.getGoldPriceForDate("2020-03-01"));
        verify(this.validation).validateDateFormat((String[]) any());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    /**
     * Method under test: {@link GoldRatesService#getGoldPriceForDate(String)}
     */
    @Test
    void testGetGoldPriceForDate2() {
        doNothing().when(this.validation).validateDateFormat((String[]) any());
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.CONTINUE));
        assertThrows(NoResultException.class, () -> this.goldRatesService.getGoldPriceForDate("2020-03-01"));
        verify(this.validation).validateDateFormat((String[]) any());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    /**
     * Method under test: {@link GoldRatesService#getGoldPriceForDate(String)}
     */
    @Test
    void testGetGoldPriceForDate3() {
        doNothing().when(this.validation).validateDateFormat((String[]) any());
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenThrow(new NoResultException("An error occurred"));
        assertThrows(NoResultException.class, () -> this.goldRatesService.getGoldPriceForDate("2020-03-01"));
        verify(this.validation).validateDateFormat((String[]) any());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    /**
     * Method under test: {@link GoldRatesService#getGoldPriceForDate(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetGoldPriceForDate4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.sda.currencyexchangeapp.service.exchange.GoldRatesService.getGoldPriceForDate(GoldRatesService.java:60)
        //   In order to prevent getGoldPriceForDate(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getGoldPriceForDate(String).
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(this.validation).validateDateFormat((String[]) any());
        when(this.aPIConnectionService.createApiConnection((String) any())).thenReturn(null);
        this.goldRatesService.getGoldPriceForDate("2020-03-01");
    }

    /**
     * Method under test: {@link GoldRatesService#getGoldPricesForPeriodOfTime(String, String)}
     */
    @Test
    void testGetGoldPricesForPeriodOfTime() {
        doNothing().when(this.validation).validateDateFormat((String[]) any());
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        assertNull(this.goldRatesService.getGoldPricesForPeriodOfTime("2020-03-01", "2020-03-01"));
        verify(this.validation).validateDateFormat((String[]) any());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    /**
     * Method under test: {@link GoldRatesService#getGoldPricesForPeriodOfTime(String, String)}
     */
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

    /**
     * Method under test: {@link GoldRatesService#getGoldPricesForPeriodOfTime(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetGoldPricesForPeriodOfTime3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "response" is null
        //       at com.sda.currencyexchangeapp.service.exchange.GoldRatesService.getGoldPricesForPeriodOfTime(GoldRatesService.java:69)
        //   In order to prevent getGoldPricesForPeriodOfTime(String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getGoldPricesForPeriodOfTime(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(this.validation).validateDateFormat((String[]) any());
        when(this.aPIConnectionService.createApiConnection((String) any())).thenReturn(null);
        this.goldRatesService.getGoldPricesForPeriodOfTime("2020-03-01", "2020-03-01");
    }
}

