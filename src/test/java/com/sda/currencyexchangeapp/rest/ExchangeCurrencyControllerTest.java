package com.sda.currencyexchangeapp.rest;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModelDto;
import com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService;

import java.time.LocalDate;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ExchangeCurrencyController.class})
@ExtendWith(SpringExtension.class)
class ExchangeCurrencyControllerTest {
    @MockBean
    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    private ExchangeCurrencyController exchangeCurrencyController;

    /**
     * Method under test: {@link ExchangeCurrencyController#count()}
     */
    @Test
    void testCount() throws Exception {
        when(this.currencyExchangeService.getDBcounter()).thenReturn(3L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/currency/count");
        MockMvcBuilders.standaloneSetup(this.exchangeCurrencyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    /**
     * Method under test: {@link ExchangeCurrencyController#count()}
     */
    @Test
    void testCount2() throws Exception {
        when(this.currencyExchangeService.getDBcounter()).thenReturn(3L);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/currency/count");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.exchangeCurrencyController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    /**
     * Method under test: {@link ExchangeCurrencyController#getAllCurrencies()}
     */
    @Test
    void testGetAllCurrencies() throws Exception {
        when(this.currencyExchangeService.getAllCurrenciesData()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/currency/api/currencies/all");
        MockMvcBuilders.standaloneSetup(this.exchangeCurrencyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ExchangeCurrencyController#getAllCurrencies()}
     */
    @Test
    void testGetAllCurrencies2() throws Exception {
        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("?");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("?");

        ArrayList<CurrencyExchangeRateModelDto> currencyExchangeRateModelDtoList = new ArrayList<>();
        currencyExchangeRateModelDtoList.add(currencyExchangeRateModelDto);
        when(this.currencyExchangeService.getAllCurrenciesData()).thenReturn(currencyExchangeRateModelDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/currency/api/currencies/all");
        MockMvcBuilders.standaloneSetup(this.exchangeCurrencyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"id\":123,\"base\":\"?\",\"target\":\"?\",\"date\":[1970,1,2],\"rate\":10.0}]"));
    }

    /**
     * Method under test: {@link ExchangeCurrencyController#getAllCurrencies()}
     */
    @Test
    void testGetAllCurrencies3() throws Exception {
        when(this.currencyExchangeService.getAllCurrenciesData()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/currency/api/currencies/all");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.exchangeCurrencyController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ExchangeCurrencyController#getCurrencyRange(String)}
     */
    @Test
    void testGetCurrencyRange() throws Exception {
        when(this.currencyExchangeService.findByBaseCurrency((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/currency/api/currencies/{base}",
                "Base");
        MockMvcBuilders.standaloneSetup(this.exchangeCurrencyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ExchangeCurrencyController#getCurrencyRange(String)}
     */
    @Test
    void testGetCurrencyRange2() throws Exception {
        CurrencyExchangeRateModelDto currencyExchangeRateModelDto = new CurrencyExchangeRateModelDto();
        currencyExchangeRateModelDto.setBase("?");
        currencyExchangeRateModelDto.setDate(LocalDate.ofEpochDay(1L));
        currencyExchangeRateModelDto.setId(123L);
        currencyExchangeRateModelDto.setRate(10.0d);
        currencyExchangeRateModelDto.setTarget("?");

        ArrayList<CurrencyExchangeRateModelDto> currencyExchangeRateModelDtoList = new ArrayList<>();
        currencyExchangeRateModelDtoList.add(currencyExchangeRateModelDto);
        when(this.currencyExchangeService.findByBaseCurrency((String) any())).thenReturn(currencyExchangeRateModelDtoList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/currency/api/currencies/{base}",
                "Base");
        MockMvcBuilders.standaloneSetup(this.exchangeCurrencyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"id\":123,\"base\":\"?\",\"target\":\"?\",\"date\":[1970,1,2],\"rate\":10.0}]"));
    }

    /**
     * Method under test: {@link ExchangeCurrencyController#getCurrencyRange(String)}
     */
    @Test
    void testGetCurrencyRange3() throws Exception {
        when(this.currencyExchangeService.findByBaseCurrency((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/currency/api/currencies/{base}", "Base");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.exchangeCurrencyController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ExchangeCurrencyController#getCurrentCurrencyRate(String, String)}
     */
    @Test
    void testGetCurrentCurrencyRate() throws Exception {
        when(this.currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation((String) any(), (String) any()))
                .thenReturn("GBP");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/currency/current")
                .param("base", "foo")
                .param("target", "foo");
        MockMvcBuilders.standaloneSetup(this.exchangeCurrencyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("GBP"));
    }

    /**
     * Method under test: {@link ExchangeCurrencyController#getCurrentCurrencyRate(String, String)}
     */
    @Test
    void testGetCurrentCurrencyRate2() throws Exception {
        when(this.currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation((String) any(), (String) any()))
                .thenReturn("GBP");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/currency/current");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("base", "foo").param("target", "foo");
        MockMvcBuilders.standaloneSetup(this.exchangeCurrencyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("GBP"));
    }

    /**
     * Method under test: {@link ExchangeCurrencyController#getHistoricalCurrencyRate(String, String, String)}
     */
    @Test
    void testGetHistoricalCurrencyRate() throws Exception {
        when(this.currencyExchangeService.getAndProcessCurrentCurrencyRateData((String) any(), (String) any(),
                (String) any())).thenReturn("GBP");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/currency/historical")
                .param("base", "foo")
                .param("date", "foo")
                .param("target", "foo");
        MockMvcBuilders.standaloneSetup(this.exchangeCurrencyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("GBP"));
    }

    /**
     * Method under test: {@link ExchangeCurrencyController#getHistoricalCurrencyRate(String, String, String)}
     */
    @Test
    void testGetHistoricalCurrencyRate2() throws Exception {
        when(this.currencyExchangeService.getAndProcessCurrentCurrencyRateData((String) any(), (String) any(),
                (String) any())).thenReturn("GBP");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/currency/historical");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("base", "foo")
                .param("date", "foo")
                .param("target", "foo");
        MockMvcBuilders.standaloneSetup(this.exchangeCurrencyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("GBP"));
    }
}

