package com.sda.currencyexchangeapp.rest;

import com.sda.currencyexchangeapp.model.gold.GoldExchangeRateModel;
import com.sda.currencyexchangeapp.service.exchange.GoldRatesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GoldRatesController.class)
public class GoldRatesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoldRatesService goldRatesService;


    @Test
    public void givenCurrentRate_whenGetCurrentGoldRate_thenReturnCurrentGoldPrice() throws Exception {
        // given - precondition or setup
        given(goldRatesService.getCurrentGoldPrice()).willReturn("270.00");

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/gold/current"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("270.00"));
    }

    @Test
    public void givenHistoricalRate_whenGetCurrentGoldRateForDate_thenReturnHistoricalGoldPrice() throws Exception {
        // given - precondition or setup
        given(goldRatesService.getGoldPriceForDate(any())).willReturn("2022-04-26");

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/gold/date")
                .param("date", "foo"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("2022-04-26"));
    }

    @Test
    public void givenHistoricalRate_whenGetCurrentGoldRateForDate_thenReturnNoResultException() throws Exception {
        // given - precondition or setup
        GoldExchangeRateModel goldExchangeRateModel = new GoldExchangeRateModel();
        goldExchangeRateModel.setDate(LocalDate.of(2022, 4, 26));
        given(goldRatesService.getGoldPriceForDate(any())).willReturn("There was no gold record for date:" + goldExchangeRateModel.getDate());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/gold/date")
                .param("date", "foo"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("There was no gold record for date:" + goldExchangeRateModel.getDate()));
    }

    @Test
    public void givenPeriodDate_whenGetCurrentGoldRateForPeriod_thenReturnHistoricalPeriodGoldPrice() throws Exception {
        // given - precondition or setup
        given(goldRatesService.getGoldPricesForPeriodOfTime(anyString(), anyString())).willReturn("Gold Prices For A Period Of Time");

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/gold/period").param("startDate", "foo1").param("endDate", "foo2"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("Gold Prices For A Period Of Time"));
    }

    @Test
    public void givenPeriodDate_whenGetCurrentGoldRateForPeriod_thenReturnNoResultException() throws Exception {
        // given - precondition or setup
        given(goldRatesService.getGoldPricesForPeriodOfTime(anyString(), anyString())).willReturn("Gold Prices For A Period Of Time");

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/gold/period")
                .param("startDate", "foo1"));

        // then - verify the output
        response.andExpect(status().is4xxClientError())
                .andDo(print());
    }

}

