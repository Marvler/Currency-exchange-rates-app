package com.sda.currencyexchangeapp.service.mapper;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.currencyexchangeapp.configuration.ConfigProperties;
import com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModel;
import com.sda.currencyexchangeapp.service.API.APIConnectionService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MapperToCurrencyModel.class, ConfigProperties.class})
@ExtendWith(SpringExtension.class)
class MapperToCurrencyModelTest {
    @MockBean
    private APIConnectionService aPIConnectionService;

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private MapperToCurrencyModel mapperToCurrencyModel;

    @MockBean
    private ObjectMapper objectMapper;

    /**
     * Method under test: {@link MapperToCurrencyModel#mapJsonToModelObject(String, String)}
     */
    @Test
    void testMapJsonToModelObject() throws JsonProcessingException {
        CurrencyExchangeRateModel currencyExchangeRateModel = new CurrencyExchangeRateModel();
        when(this.objectMapper.readValue((String) any(), (Class<CurrencyExchangeRateModel>) any()))
                .thenReturn(currencyExchangeRateModel);
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        assertSame(currencyExchangeRateModel, this.mapperToCurrencyModel.mapJsonToModelObject("Base", "Target"));
        verify(this.objectMapper).readValue((String) any(), (Class<CurrencyExchangeRateModel>) any());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    /**
     * Method under test: {@link MapperToCurrencyModel#mapJsonToModelObject(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMapJsonToModelObject2() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.core.JsonProcessingException$MockitoMock$830703274
        //   In order to prevent mapJsonToModelObject(String, String)
        //   from throwing JsonProcessingException$MockitoMock$830703274, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   mapJsonToModelObject(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.objectMapper.readValue((String) any(), (Class<CurrencyExchangeRateModel>) any()))
                .thenThrow(mock(JsonProcessingException.class));
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        this.mapperToCurrencyModel.mapJsonToModelObject("Base", "Target");
    }

    /**
     * Method under test: {@link MapperToCurrencyModel#mapJsonToModelObject(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMapJsonToModelObject3() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because the return value of "com.sda.currencyexchangeapp.service.API.APIConnectionService.createApiConnection(String)" is null
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToCurrencyModel.mapJsonToModelObject(MapperToCurrencyModel.java:29)
        //   In order to prevent mapJsonToModelObject(String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   mapJsonToModelObject(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.objectMapper.readValue((String) any(), (Class<Object>) any())).thenReturn("Value");
        when(this.objectMapper.readValue((String) any(), (Class<CurrencyExchangeRateModel>) any()))
                .thenReturn(new CurrencyExchangeRateModel());
        when(this.aPIConnectionService.createApiConnection((String) any())).thenReturn(null);
        this.mapperToCurrencyModel.mapJsonToModelObject("Base", "Target");
    }

    /**
     * Method under test: {@link MapperToCurrencyModel#mapJsonToModelObject(String, String, String)}
     */
    @Test
    void testMapJsonToModelObject4() throws JsonProcessingException {
        CurrencyExchangeRateModel currencyExchangeRateModel = new CurrencyExchangeRateModel();
        when(this.objectMapper.readValue((String) any(), (Class<CurrencyExchangeRateModel>) any()))
                .thenReturn(currencyExchangeRateModel);
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        assertSame(currencyExchangeRateModel,
                this.mapperToCurrencyModel.mapJsonToModelObject("2020-03-01", "Base", "Target"));
        verify(this.objectMapper).readValue((String) any(), (Class<CurrencyExchangeRateModel>) any());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    /**
     * Method under test: {@link MapperToCurrencyModel#mapJsonToModelObject(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMapJsonToModelObject5() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.core.JsonProcessingException$MockitoMock$1870299429
        //   In order to prevent mapJsonToModelObject(String, String, String)
        //   from throwing JsonProcessingException$MockitoMock$1870299429, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   mapJsonToModelObject(String, String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.objectMapper.readValue((String) any(), (Class<CurrencyExchangeRateModel>) any()))
                .thenThrow(mock(JsonProcessingException.class));
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        this.mapperToCurrencyModel.mapJsonToModelObject("2020-03-01", "Base", "Target");
    }

    /**
     * Method under test: {@link MapperToCurrencyModel#mapJsonToModelObject(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMapJsonToModelObject6() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because the return value of "com.sda.currencyexchangeapp.service.API.APIConnectionService.createApiConnection(String)" is null
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToCurrencyModel.mapJsonToModelObject(MapperToCurrencyModel.java:33)
        //   In order to prevent mapJsonToModelObject(String, String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   mapJsonToModelObject(String, String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.objectMapper.readValue((String) any(), (Class<Object>) any())).thenReturn("Value");
        when(this.objectMapper.readValue((String) any(), (Class<CurrencyExchangeRateModel>) any()))
                .thenReturn(new CurrencyExchangeRateModel());
        when(this.aPIConnectionService.createApiConnection((String) any())).thenReturn(null);
        this.mapperToCurrencyModel.mapJsonToModelObject("2020-03-01", "Base", "Target");
    }
}

