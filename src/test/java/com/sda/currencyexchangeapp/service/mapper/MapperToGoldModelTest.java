package com.sda.currencyexchangeapp.service.mapper;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.currencyexchangeapp.configuration.ConfigProperties;
import com.sda.currencyexchangeapp.model.gold.GoldExchangeRateModel;
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

@ContextConfiguration(classes = {MapperToGoldModel.class, ConfigProperties.class})
@ExtendWith(SpringExtension.class)
class MapperToGoldModelTest {
    @MockBean
    private APIConnectionService aPIConnectionService;

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private MapperToGoldModel mapperToGoldModel;

    @MockBean
    private ObjectMapper objectMapper;

    /**
     * Method under test: {@link MapperToGoldModel#mapJsonToModelObject()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMapJsonToModelObject() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.replace(java.lang.CharSequence, java.lang.CharSequence)" because the return value of "org.springframework.http.ResponseEntity.getBody()" is null
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToGoldModel.getFormattedBody(MapperToGoldModel.java:33)
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToGoldModel.mapJsonToModelObject(MapperToGoldModel.java:25)
        //   In order to prevent mapJsonToModelObject()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   mapJsonToModelObject().
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        this.mapperToGoldModel.mapJsonToModelObject();
    }

    /**
     * Method under test: {@link MapperToGoldModel#mapJsonToModelObject()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMapJsonToModelObject2() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because the return value of "com.sda.currencyexchangeapp.service.API.APIConnectionService.createApiConnection(String)" is null
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToGoldModel.getFormattedBody(MapperToGoldModel.java:33)
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToGoldModel.mapJsonToModelObject(MapperToGoldModel.java:25)
        //   In order to prevent mapJsonToModelObject()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   mapJsonToModelObject().
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.aPIConnectionService.createApiConnection((String) any())).thenReturn(null);
        this.mapperToGoldModel.mapJsonToModelObject();
    }

    /**
     * Method under test: {@link MapperToGoldModel#mapJsonToModelObject()}
     */
    @Test
    void testMapJsonToModelObject3() throws JsonProcessingException {
        GoldExchangeRateModel goldExchangeRateModel = new GoldExchangeRateModel();
        when(this.objectMapper.readValue((String) any(), (Class<GoldExchangeRateModel>) any()))
                .thenReturn(goldExchangeRateModel);
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>("https://example.org/example", HttpStatus.CONTINUE));
        assertSame(goldExchangeRateModel, this.mapperToGoldModel.mapJsonToModelObject());
        verify(this.objectMapper).readValue((String) any(), (Class<GoldExchangeRateModel>) any());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    /**
     * Method under test: {@link MapperToGoldModel#mapJsonToModelObject()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMapJsonToModelObject4() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.StringIndexOutOfBoundsException: begin 1, end -1, length 0
        //       at java.lang.String.checkBoundsBeginEnd(String.java:4601)
        //       at java.lang.String.substring(String.java:2704)
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToGoldModel.getFormattedBody(MapperToGoldModel.java:34)
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToGoldModel.mapJsonToModelObject(MapperToGoldModel.java:25)
        //   In order to prevent mapJsonToModelObject()
        //   from throwing StringIndexOutOfBoundsException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   mapJsonToModelObject().
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.objectMapper.readValue((String) any(), (Class<Object>) any())).thenReturn("Value");
        when(this.objectMapper.readValue((String) any(), (Class<GoldExchangeRateModel>) any()))
                .thenReturn(new GoldExchangeRateModel());
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>("", HttpStatus.CONTINUE));
        this.mapperToGoldModel.mapJsonToModelObject();
    }

    /**
     * Method under test: {@link MapperToGoldModel#mapJsonToModelObject(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMapJsonToModelObject5() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.replace(java.lang.CharSequence, java.lang.CharSequence)" because the return value of "org.springframework.http.ResponseEntity.getBody()" is null
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToGoldModel.getFormattedBody(MapperToGoldModel.java:33)
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToGoldModel.mapJsonToModelObject(MapperToGoldModel.java:29)
        //   In order to prevent mapJsonToModelObject(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   mapJsonToModelObject(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        this.mapperToGoldModel.mapJsonToModelObject("2020-03-01");
    }

    /**
     * Method under test: {@link MapperToGoldModel#mapJsonToModelObject(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMapJsonToModelObject6() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because the return value of "com.sda.currencyexchangeapp.service.API.APIConnectionService.createApiConnection(String)" is null
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToGoldModel.getFormattedBody(MapperToGoldModel.java:33)
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToGoldModel.mapJsonToModelObject(MapperToGoldModel.java:29)
        //   In order to prevent mapJsonToModelObject(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   mapJsonToModelObject(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.aPIConnectionService.createApiConnection((String) any())).thenReturn(null);
        this.mapperToGoldModel.mapJsonToModelObject("2020-03-01");
    }

    /**
     * Method under test: {@link MapperToGoldModel#mapJsonToModelObject(String)}
     */
    @Test
    void testMapJsonToModelObject7() throws JsonProcessingException {
        GoldExchangeRateModel goldExchangeRateModel = new GoldExchangeRateModel();
        when(this.objectMapper.readValue((String) any(), (Class<GoldExchangeRateModel>) any()))
                .thenReturn(goldExchangeRateModel);
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>("https://example.org/example", HttpStatus.CONTINUE));
        assertSame(goldExchangeRateModel, this.mapperToGoldModel.mapJsonToModelObject("2020-03-01"));
        verify(this.objectMapper).readValue((String) any(), (Class<GoldExchangeRateModel>) any());
        verify(this.aPIConnectionService).createApiConnection((String) any());
    }

    /**
     * Method under test: {@link MapperToGoldModel#mapJsonToModelObject(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMapJsonToModelObject8() throws JsonProcessingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.StringIndexOutOfBoundsException: begin 1, end 0, length 1
        //       at java.lang.String.checkBoundsBeginEnd(String.java:4601)
        //       at java.lang.String.substring(String.java:2704)
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToGoldModel.getFormattedBody(MapperToGoldModel.java:34)
        //       at com.sda.currencyexchangeapp.service.mapper.MapperToGoldModel.mapJsonToModelObject(MapperToGoldModel.java:29)
        //   In order to prevent mapJsonToModelObject(String)
        //   from throwing StringIndexOutOfBoundsException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   mapJsonToModelObject(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(this.objectMapper.readValue((String) any(), (Class<Object>) any())).thenReturn("Value");
        when(this.objectMapper.readValue((String) any(), (Class<GoldExchangeRateModel>) any()))
                .thenReturn(new GoldExchangeRateModel());
        when(this.aPIConnectionService.createApiConnection((String) any()))
                .thenReturn(new ResponseEntity<>("<", HttpStatus.CONTINUE));
        this.mapperToGoldModel.mapJsonToModelObject("2020-03-01");
    }
}

