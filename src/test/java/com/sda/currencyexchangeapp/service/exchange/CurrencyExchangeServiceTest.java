package com.sda.currencyexchangeapp.service.exchange;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
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
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrencyExchangeRateAfterValidation(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAndProcessCurrencyExchangeRateAfterValidation() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Map.keySet()" because "that" is null
        //       at com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModel.toString(CurrencyExchangeRateModel.java:28)
        //       at com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService.getAndProcessCurrentCurrencyRateData(CurrencyExchangeService.java:59)
        //       at com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation(CurrencyExchangeService.java:45)
        //   In order to prevent getAndProcessCurrencyExchangeRateAfterValidation(String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAndProcessCurrencyExchangeRateAfterValidation(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.validation.validateIfCurrencyExists((String) any(), (String) any())).thenReturn(true);
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(new CurrencyExchangeRateModel());

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setId(123L);
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenReturn(true);
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any())).thenReturn(currencyExchangeRateModelDto1);
        this.currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation("Base", "Target");
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrencyExchangeRateAfterValidation(String, String)}
     */
    @Test
    void testGetAndProcessCurrencyExchangeRateAfterValidation2() throws JsonProcessingException {
        when(this.validation.validateIfCurrencyExists((String) any(), (String) any())).thenReturn(true);
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(new CurrencyExchangeRateModel());

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any()))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        assertThrows(CurrencyProcessingException.class,
                () -> this.currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation("Base", "Target"));
        verify(this.validation).validateIfCurrencyExists((String) any(), (String) any());
        verify(this.mapperToCurrencyModel).mapJsonToModelObject((String) any(), (String) any());
        verify(this.mapperToCurrencyDTO).convertModelToDTO((CurrencyExchangeRateModel) any());
        verify(this.currencyRepository)
                .exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any());
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrencyExchangeRateAfterValidation(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAndProcessCurrencyExchangeRateAfterValidation3() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModel.toString(CurrencyExchangeRateModel.java:28)
        //       at com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService.getAndProcessCurrentCurrencyRateData(CurrencyExchangeService.java:59)
        //       at com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation(CurrencyExchangeService.java:45)
        //   In order to prevent getAndProcessCurrencyExchangeRateAfterValidation(String, String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAndProcessCurrencyExchangeRateAfterValidation(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.validation.validateIfCurrencyExists((String) any(), (String) any())).thenReturn(true);

        CurrencyExchangeRateModel currencyExchangeRateModel = new CurrencyExchangeRateModel();
        currencyExchangeRateModel.setRates(new HashMap<>());
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(currencyExchangeRateModel);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setId(123L);
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenReturn(true);
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any())).thenReturn(currencyExchangeRateModelDto1);
        this.currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation("Base", "Target");
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrencyExchangeRateAfterValidation(String, String)}
     */
    @Test
    void testGetAndProcessCurrencyExchangeRateAfterValidation4() throws JsonProcessingException {
        when(this.validation.validateIfCurrencyExists((String) any(), (String) any())).thenReturn(true);
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(mock(CurrencyExchangeRateModel.class));

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setId(123L);
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenReturn(true);
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any())).thenReturn(currencyExchangeRateModelDto1);
        this.currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation("Base", "Target");
        verify(this.validation).validateIfCurrencyExists((String) any(), (String) any());
        verify(this.mapperToCurrencyModel).mapJsonToModelObject((String) any(), (String) any());
        verify(this.mapperToCurrencyDTO).convertModelToDTO((CurrencyExchangeRateModel) any());
        verify(this.currencyRepository)
                .exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any());
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrencyExchangeRateAfterValidation(String, String)}
     */
    @Test
    void testGetAndProcessCurrencyExchangeRateAfterValidation5() throws JsonProcessingException {
        when(this.validation.validateIfCurrencyExists((String) any(), (String) any())).thenReturn(false);
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(new CurrencyExchangeRateModel());

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any()))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        assertThrows(CurrencyProcessingException.class,
                () -> this.currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation("Base", "Target"));
        verify(this.validation).validateIfCurrencyExists((String) any(), (String) any());
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrencyExchangeRateAfterValidation(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAndProcessCurrencyExchangeRateAfterValidation6() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Date.toInstant()" because "this.date" is null
        //       at com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModel.toString(CurrencyExchangeRateModel.java:30)
        //       at com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService.getAndProcessCurrentCurrencyRateData(CurrencyExchangeService.java:59)
        //       at com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation(CurrencyExchangeService.java:45)
        //   In order to prevent getAndProcessCurrencyExchangeRateAfterValidation(String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAndProcessCurrencyExchangeRateAfterValidation(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.validation.validateIfCurrencyExists((String) any(), (String) any())).thenReturn(true);

        HashMap<String, Double> stringResultDoubleMap = new HashMap<>();
        stringResultDoubleMap.put("Key", 10.0d);

        CurrencyExchangeRateModel currencyExchangeRateModel = new CurrencyExchangeRateModel();
        currencyExchangeRateModel.setRates(stringResultDoubleMap);
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(currencyExchangeRateModel);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setId(123L);
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenReturn(true);
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any())).thenReturn(currencyExchangeRateModelDto1);
        this.currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation("Base", "Target");
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrencyExchangeRateAfterValidation(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAndProcessCurrencyExchangeRateAfterValidation7() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModel.toString(CurrencyExchangeRateModel.java:28)
        //       at com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService.getAndProcessCurrentCurrencyRateData(CurrencyExchangeService.java:59)
        //       at com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation(CurrencyExchangeService.java:45)
        //   In order to prevent getAndProcessCurrencyExchangeRateAfterValidation(String, String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAndProcessCurrencyExchangeRateAfterValidation(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.validation.validateIfCurrencyExists((String) any(), (String) any())).thenReturn(true);

        CurrencyExchangeRateModel currencyExchangeRateModel = new CurrencyExchangeRateModel();
        currencyExchangeRateModel.setRates(new HashMap<>());
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(currencyExchangeRateModel);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setId(123L);
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenReturn(false);
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any())).thenReturn(currencyExchangeRateModelDto1);
        this.currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation("Base", "Target");
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrentCurrencyRateData(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAndProcessCurrentCurrencyRateData() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Map.keySet()" because "that" is null
        //       at com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModel.toString(CurrencyExchangeRateModel.java:28)
        //       at com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService.getAndProcessCurrentCurrencyRateData(CurrencyExchangeService.java:59)
        //   In order to prevent getAndProcessCurrentCurrencyRateData(String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAndProcessCurrentCurrencyRateData(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(new CurrencyExchangeRateModel());

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setId(123L);
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenReturn(true);
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any())).thenReturn(currencyExchangeRateModelDto1);
        this.currencyExchangeService.getAndProcessCurrentCurrencyRateData("Base", "Target");
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrentCurrencyRateData(String, String)}
     */
    @Test
    void testGetAndProcessCurrentCurrencyRateData2() throws JsonProcessingException {
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(new CurrencyExchangeRateModel());

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any()))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        assertThrows(CurrencyProcessingException.class,
                () -> this.currencyExchangeService.getAndProcessCurrentCurrencyRateData("Base", "Target"));
        verify(this.mapperToCurrencyModel).mapJsonToModelObject((String) any(), (String) any());
        verify(this.mapperToCurrencyDTO).convertModelToDTO((CurrencyExchangeRateModel) any());
        verify(this.currencyRepository)
                .exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any());
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrentCurrencyRateData(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAndProcessCurrentCurrencyRateData3() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModel.toString(CurrencyExchangeRateModel.java:28)
        //       at com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService.getAndProcessCurrentCurrencyRateData(CurrencyExchangeService.java:59)
        //   In order to prevent getAndProcessCurrentCurrencyRateData(String, String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAndProcessCurrentCurrencyRateData(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        CurrencyExchangeRateModel currencyExchangeRateModel = new CurrencyExchangeRateModel();
        currencyExchangeRateModel.setRates(new HashMap<>());
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(currencyExchangeRateModel);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setId(123L);
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenReturn(true);
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any())).thenReturn(currencyExchangeRateModelDto1);
        this.currencyExchangeService.getAndProcessCurrentCurrencyRateData("Base", "Target");
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrentCurrencyRateData(String, String)}
     */
    @Test
    void testGetAndProcessCurrentCurrencyRateData4() throws JsonProcessingException {
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(mock(CurrencyExchangeRateModel.class));

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setId(123L);
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenReturn(true);
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any())).thenReturn(currencyExchangeRateModelDto1);
        this.currencyExchangeService.getAndProcessCurrentCurrencyRateData("Base", "Target");
        verify(this.mapperToCurrencyModel).mapJsonToModelObject((String) any(), (String) any());
        verify(this.mapperToCurrencyDTO).convertModelToDTO((CurrencyExchangeRateModel) any());
        verify(this.currencyRepository)
                .exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any());
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrentCurrencyRateData(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAndProcessCurrentCurrencyRateData5() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Date.toInstant()" because "this.date" is null
        //       at com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModel.toString(CurrencyExchangeRateModel.java:30)
        //       at com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService.getAndProcessCurrentCurrencyRateData(CurrencyExchangeService.java:59)
        //   In order to prevent getAndProcessCurrentCurrencyRateData(String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAndProcessCurrentCurrencyRateData(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        HashMap<String, Double> stringResultDoubleMap = new HashMap<>();
        stringResultDoubleMap.put("Key", 10.0d);

        CurrencyExchangeRateModel currencyExchangeRateModel = new CurrencyExchangeRateModel();
        currencyExchangeRateModel.setRates(stringResultDoubleMap);
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(currencyExchangeRateModel);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setId(123L);
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenReturn(true);
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any())).thenReturn(currencyExchangeRateModelDto1);
        this.currencyExchangeService.getAndProcessCurrentCurrencyRateData("Base", "Target");
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrentCurrencyRateData(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAndProcessCurrentCurrencyRateData6() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModel.toString(CurrencyExchangeRateModel.java:28)
        //       at com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService.getAndProcessCurrentCurrencyRateData(CurrencyExchangeService.java:59)
        //   In order to prevent getAndProcessCurrentCurrencyRateData(String, String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAndProcessCurrentCurrencyRateData(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        CurrencyExchangeRateModel currencyExchangeRateModel = new CurrencyExchangeRateModel();
        currencyExchangeRateModel.setRates(new HashMap<>());
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any()))
                .thenReturn(currencyExchangeRateModel);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setId(123L);
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenReturn(false);
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any())).thenReturn(currencyExchangeRateModelDto1);
        this.currencyExchangeService.getAndProcessCurrentCurrencyRateData("Base", "Target");
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrentCurrencyRateData(String, String, String)}
     */
    @Test
    void testGetAndProcessCurrentCurrencyRateData7() throws JsonProcessingException {
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any(), (String) any()))
                .thenReturn(new CurrencyExchangeRateModel());

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setId(123L);
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenReturn(true);
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any())).thenReturn(currencyExchangeRateModelDto1);
        assertEquals("Base currency: Base\nTarget currency: Target\nDate: 1970-01-02\nRate: 10,0000",
                this.currencyExchangeService.getAndProcessCurrentCurrencyRateData("2020-03-01", "Base", "Target"));
        verify(this.mapperToCurrencyModel).mapJsonToModelObject((String) any(), (String) any(), (String) any());
        verify(this.mapperToCurrencyDTO).convertModelToDTO((CurrencyExchangeRateModel) any());
        verify(this.currencyRepository)
                .exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any());
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrentCurrencyRateData(String, String, String)}
     */
    @Test
    void testGetAndProcessCurrentCurrencyRateData8() throws JsonProcessingException {
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any(), (String) any()))
                .thenReturn(new CurrencyExchangeRateModel());

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any()))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        assertThrows(CurrencyProcessingException.class,
                () -> this.currencyExchangeService.getAndProcessCurrentCurrencyRateData("2020-03-01", "Base", "Target"));
        verify(this.mapperToCurrencyModel).mapJsonToModelObject((String) any(), (String) any(), (String) any());
        verify(this.mapperToCurrencyDTO).convertModelToDTO((CurrencyExchangeRateModel) any());
        verify(this.currencyRepository)
                .exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any());
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrentCurrencyRateData(String, String, String)}
     */
    @Test
    void testGetAndProcessCurrentCurrencyRateData9() throws JsonProcessingException {
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any(), (String) any()))
                .thenReturn(new CurrencyExchangeRateModel());
        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = mock(CurrencyExchangeRateModelDto.class);
        doNothing().when(currencyExchangeRateModelDto).setBase((String) any());
        doNothing().when(currencyExchangeRateModelDto).setDate((LocalDate) any());
        doNothing().when(currencyExchangeRateModelDto).setId((Long) any());
        doNothing().when(currencyExchangeRateModelDto).setRate((Double) any());
        doNothing().when(currencyExchangeRateModelDto).setTarget((String) any());
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setId(123L);
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenReturn(true);
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any())).thenReturn(currencyExchangeRateModelDto1);
        this.currencyExchangeService.getAndProcessCurrentCurrencyRateData("2020-03-01", "Base", "Target");
        verify(this.mapperToCurrencyModel).mapJsonToModelObject((String) any(), (String) any(), (String) any());
        verify(this.mapperToCurrencyDTO).convertModelToDTO((CurrencyExchangeRateModel) any());
        verify(currencyExchangeRateModelDto).setBase((String) any());
        verify(currencyExchangeRateModelDto).setDate((LocalDate) any());
        verify(currencyExchangeRateModelDto).setId((Long) any());
        verify(currencyExchangeRateModelDto).setRate((Double) any());
        verify(currencyExchangeRateModelDto).setTarget((String) any());
        verify(this.currencyRepository)
                .exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any());
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAndProcessCurrentCurrencyRateData(String, String, String)}
     */
    @Test
    void testGetAndProcessCurrentCurrencyRateData10() throws JsonProcessingException {
        when(this.mapperToCurrencyModel.mapJsonToModelObject((String) any(), (String) any(), (String) any()))
                .thenReturn(new CurrencyExchangeRateModel());
        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = mock(CurrencyExchangeRateModelDto.class);
        doNothing().when(currencyExchangeRateModelDto).setBase((String) any());
        doNothing().when(currencyExchangeRateModelDto).setDate((LocalDate) any());
        doNothing().when(currencyExchangeRateModelDto).setId((Long) any());
        doNothing().when(currencyExchangeRateModelDto).setRate((Double) any());
        doNothing().when(currencyExchangeRateModelDto).setTarget((String) any());
        currencyExchangeRateModelDto.setBase("Base");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("Target");
        when(this.mapperToCurrencyDTO.convertModelToDTO((CurrencyExchangeRateModel) any()))
                .thenReturn(currencyExchangeRateModelDto);

        CurrencyExchangeRateModelDto currencyExchangeRateModelDto1 = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto1.setBase("Base");
        currencyExchangeRateModelDto1.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto1.setId(123L);
        currencyExchangeRateModelDto1.setRate(10.0d);
        currencyExchangeRateModelDto1.setTarget("Target");
        when(this.currencyRepository.exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any()))
                .thenReturn(false);
        when(this.currencyRepository.save((CurrencyExchangeRateModelDto) any())).thenReturn(currencyExchangeRateModelDto1);
        this.currencyExchangeService.getAndProcessCurrentCurrencyRateData("2020-03-01", "Base", "Target");
        verify(this.mapperToCurrencyModel).mapJsonToModelObject((String) any(), (String) any(), (String) any());
        verify(this.mapperToCurrencyDTO).convertModelToDTO((CurrencyExchangeRateModel) any());
        verify(currencyExchangeRateModelDto).setBase((String) any());
        verify(currencyExchangeRateModelDto).setDate((LocalDate) any());
        verify(currencyExchangeRateModelDto).setId((Long) any());
        verify(currencyExchangeRateModelDto).setRate((Double) any());
        verify(currencyExchangeRateModelDto).setTarget((String) any());
        verify(this.currencyRepository)
                .exists((org.springframework.data.domain.Example<CurrencyExchangeRateModelDto>) any());
        verify(this.currencyRepository).save((CurrencyExchangeRateModelDto) any());
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAllCurrenciesData()}
     */
    @Test
    void testGetAllCurrenciesData() {
        ArrayList<CurrencyExchangeRateModelDto> currencyExchangeRateModelDtoList = new ArrayList<>();
        when(this.currencyRepository.findAll()).thenReturn(currencyExchangeRateModelDtoList);
        List<CurrencyExchangeRateModelDto> actualAllCurrenciesData = this.currencyExchangeService.getAllCurrenciesData();
        assertSame(currencyExchangeRateModelDtoList, actualAllCurrenciesData);
        assertTrue(actualAllCurrenciesData.isEmpty());
        verify(this.currencyRepository).findAll();
    }

    /**
     * Method under test: {@link CurrencyExchangeService#getAllCurrenciesData()}
     */
    @Test
    void testGetAllCurrenciesData2() {
        when(this.currencyRepository.findAll()).thenThrow(new CurrencyProcessingException("An error occurred"));
        assertThrows(CurrencyProcessingException.class, () -> this.currencyExchangeService.getAllCurrenciesData());
        verify(this.currencyRepository).findAll();
    }

    /**
     * Method under test: {@link CurrencyExchangeService#findByBaseCurrency(String)}
     */
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

    /**
     * Method under test: {@link CurrencyExchangeService#findByBaseCurrency(String)}
     */
    @Test
    void testFindByBaseCurrency2() {
        when(this.currencyRepository.findByBase((String) any()))
                .thenThrow(new CurrencyProcessingException("An error occurred"));
        assertThrows(CurrencyProcessingException.class, () -> this.currencyExchangeService.findByBaseCurrency("GBP"));
        verify(this.currencyRepository).findByBase((String) any());
    }
}

