package com.sda.currencyexchangeapp.rest;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.sda.currencyexchangeapp.service.API.APICountService;
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

@ContextConfiguration(classes = {ApiCountController.class})
@ExtendWith(SpringExtension.class)
class ApiCountControllerTest {
    @MockBean
    private APICountService aPICountService;

    @Autowired
    private ApiCountController apiCountController;

    /**
     * Method under test: {@link ApiCountController#getNumberOfApiCallsForCurrenciesInPeriodOfTime()}
     */
    @Test
    void testGetNumberOfApiCallsForCurrenciesInPeriodOfTime() throws Exception {
        when(this.aPICountService.getTotalNumberOfApiCalls((String) any())).thenReturn("42");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/count/currencies/historical");
        MockMvcBuilders.standaloneSetup(this.apiCountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Number of API calls for getting data of currency in period of time: 42"));
    }

    /**
     * Method under test: {@link ApiCountController#getNumberOfApiCallsForCurrenciesInPeriodOfTime()}
     */
    @Test
    void testGetNumberOfApiCallsForCurrenciesInPeriodOfTime2() throws Exception {
        when(this.aPICountService.getTotalNumberOfApiCalls((String) any())).thenReturn("42");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/count/currencies/historical");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.apiCountController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Number of API calls for getting data of currency in period of time: 42"));
    }

    /**
     * Method under test: {@link ApiCountController#getNumberOfApiCallsForCurrentCurrenciesData()}
     */
    @Test
    void testGetNumberOfApiCallsForCurrentCurrenciesData() throws Exception {
        when(this.aPICountService.getTotalNumberOfApiCalls((String) any())).thenReturn("42");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/count/currencies/current");
        MockMvcBuilders.standaloneSetup(this.apiCountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Number of API calls for getting current data regarding currencies: 42"));
    }

    /**
     * Method under test: {@link ApiCountController#getNumberOfApiCallsForCurrentCurrenciesData()}
     */
    @Test
    void testGetNumberOfApiCallsForCurrentCurrenciesData2() throws Exception {
        when(this.aPICountService.getTotalNumberOfApiCalls((String) any())).thenReturn("42");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/count/currencies/current");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.apiCountController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Number of API calls for getting current data regarding currencies: 42"));
    }

    /**
     * Method under test: {@link ApiCountController#getNumberOfApiCallsForCurrentGold()}
     */
    @Test
    void testGetNumberOfApiCallsForCurrentGold() throws Exception {
        when(this.aPICountService.getTotalNumberOfApiCalls((String) any())).thenReturn("42");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/count/gold/current");
        MockMvcBuilders.standaloneSetup(this.apiCountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(
                        MockMvcResultMatchers.content().string("Number of API calls for getting current date of Gold rate: 42"));
    }

    /**
     * Method under test: {@link ApiCountController#getNumberOfApiCallsForCurrentGold()}
     */
    @Test
    void testGetNumberOfApiCallsForCurrentGold2() throws Exception {
        when(this.aPICountService.getTotalNumberOfApiCalls((String) any())).thenReturn("42");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/count/gold/current");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.apiCountController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(
                        MockMvcResultMatchers.content().string("Number of API calls for getting current date of Gold rate: 42"));
    }

    /**
     * Method under test: {@link ApiCountController#getNumberOfApiCallsForGoldInPeriodOfTime()}
     */
    @Test
    void testGetNumberOfApiCallsForGoldInPeriodOfTime() throws Exception {
        when(this.aPICountService.getTotalNumberOfApiCalls((String) any())).thenReturn("42");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/count/gold/dates");
        MockMvcBuilders.standaloneSetup(this.apiCountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Number of API calls for getting data of Gold price in period of time: 84"));
    }

    /**
     * Method under test: {@link ApiCountController#getNumberOfApiCallsForGoldInPeriodOfTime()}
     */
    @Test
    void testGetNumberOfApiCallsForGoldInPeriodOfTime2() throws Exception {
        when(this.aPICountService.getTotalNumberOfApiCalls((String) any())).thenReturn("42");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/count/gold/dates");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.apiCountController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("Number of API calls for getting data of Gold price in period of time: 84"));
    }
}

