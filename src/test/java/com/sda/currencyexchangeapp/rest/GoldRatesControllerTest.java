package com.sda.currencyexchangeapp.rest;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.sda.currencyexchangeapp.service.exchange.GoldRatesService;
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

@ContextConfiguration(classes = {GoldRatesController.class})
@ExtendWith(SpringExtension.class)
class GoldRatesControllerTest {
    @Autowired
    private GoldRatesController goldRatesController;

    @MockBean
    private GoldRatesService goldRatesService;

    /**
     * Method under test: {@link GoldRatesController#getCurrentGoldRate()}
     */
    @Test
    void testGetCurrentGoldRate() throws Exception {
        when(this.goldRatesService.getCurrentGoldPrice()).thenReturn("Current Gold Price");
        when(this.goldRatesService.processCurrentGoldRateData()).thenReturn("Process Current Gold Rate Data");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/gold/current");
        MockMvcBuilders.standaloneSetup(this.goldRatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Current Gold Price"));
    }

    /**
     * Method under test: {@link GoldRatesController#getCurrentGoldRate()}
     */
    @Test
    void testGetCurrentGoldRate2() throws Exception {
        when(this.goldRatesService.getCurrentGoldPrice()).thenReturn("Current Gold Price");
        when(this.goldRatesService.processCurrentGoldRateData()).thenReturn("Process Current Gold Rate Data");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/gold/current");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.goldRatesController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Current Gold Price"));
    }

    /**
     * Method under test: {@link GoldRatesController#getCurrentGoldRateForDate(String)}
     */
    @Test
    void testGetCurrentGoldRateForDate() throws Exception {
        when(this.goldRatesService.getGoldPriceForDate((String) any())).thenReturn("2020-03-01");
        when(this.goldRatesService.processGoldRateDataForDate((String) any())).thenReturn("2020-03-01");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/gold/date").param("date", "foo");
        MockMvcBuilders.standaloneSetup(this.goldRatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("2020-03-01"));
    }

    /**
     * Method under test: {@link GoldRatesController#getCurrentGoldRateForDate(String)}
     */
    @Test
    void testGetCurrentGoldRateForDate2() throws Exception {
        when(this.goldRatesService.getGoldPriceForDate((String) any())).thenReturn("2020-03-01");
        when(this.goldRatesService.processGoldRateDataForDate((String) any())).thenReturn("2020-03-01");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/gold/date");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("date", "foo");
        MockMvcBuilders.standaloneSetup(this.goldRatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("2020-03-01"));
    }

    /**
     * Method under test: {@link GoldRatesController#getCurrentGoldRateForPeriod(String, String)}
     */
    @Test
    void testGetCurrentGoldRateForPeriod() throws Exception {
        when(this.goldRatesService.getGoldPricesForPeriodOfTime((String) any(), (String) any()))
                .thenReturn("Gold Prices For Period Of Time");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/gold/period")
                .param("endDate", "foo")
                .param("startDate", "foo");
        MockMvcBuilders.standaloneSetup(this.goldRatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Gold Prices For Period Of Time"));
    }

    /**
     * Method under test: {@link GoldRatesController#getCurrentGoldRateForPeriod(String, String)}
     */
    @Test
    void testGetCurrentGoldRateForPeriod2() throws Exception {
        when(this.goldRatesService.getGoldPricesForPeriodOfTime((String) any(), (String) any()))
                .thenReturn("Gold Prices For Period Of Time");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/gold/period");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("endDate", "foo").param("startDate", "foo");
        MockMvcBuilders.standaloneSetup(this.goldRatesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Gold Prices For Period Of Time"));
    }
}

