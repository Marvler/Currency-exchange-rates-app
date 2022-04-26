package com.sda.currencyexchangeapp.rest;

import com.sda.currencyexchangeapp.model.currency.CurrencyExchangeRateModelDto;
import com.sda.currencyexchangeapp.model.exception.CurrencyProcessingException;
import com.sda.currencyexchangeapp.model.exception.NoResultException;
import com.sda.currencyexchangeapp.service.exchange.CurrencyExchangeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExchangeCurrencyController.class)
class ExchangeCurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyExchangeService currencyExchangeService;


    @Test
    public void givenCurrentCurrencyRate_whenGetCurrentCurrencyRate_thenReturnCurrentCurrencyRate() throws Exception {
        // given - precondition or setup
        given(currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation(anyString(), anyString())).willReturn("100$");

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/currency/current")
                .param("base", "foo1")
                .param("target", "foo2"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("100$"));
    }

    @Test
    public void givenCurrentCurrencyRate_whenGetCurrentCurrencyRate_thenReturnCurrencyProcessingException() throws Exception {
        // given - precondition or setup
        given(currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation(anyString(), anyString()))
                .willReturn("Base currency and Target currency are the same. Guess the result :). DB not updated.");

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/currency/current")
                .param("base", "foo1")
                .param("target", "foo2"));
        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("Base currency and Target currency are the same. Guess the result :). DB not updated."));
    }

    @Test
    public void givenCurrentCurrencyRate_whenGetCurrentCurrencyRate_thenReturnCurrencyProcessingExceptionConcerningEqualBaseAndTarget() throws Exception {
        // given - precondition or setup
        CurrencyExchangeRateModelDto currencyExchangeRateModel = new CurrencyExchangeRateModelDto(1L, "USD", "USD", LocalDate.now(), 4.30);
        given(currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation(currencyExchangeRateModel.getBase(), currencyExchangeRateModel.getTarget()))
                .willThrow(new CurrencyProcessingException("Exception occurred: Base & Target are the same"));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/currency/current")
                .param("base", currencyExchangeRateModel.getBase())
                .param("target", currencyExchangeRateModel.getTarget()));
        // then - verify the output
        response.andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void givenCurrentCurrencyRate_whenGetCurrentCurrencyRate_thenReturnCurrencyProcessingExceptionConcerning() throws Exception {
        // given - precondition or setup
        CurrencyExchangeRateModelDto currencyExchangeRateModel = new CurrencyExchangeRateModelDto(1L, "xyz", "123", LocalDate.now(), 9.99);
        given(currencyExchangeService.getAndProcessCurrencyExchangeRateAfterValidation(anyString(), anyString()))
                .willThrow(new NoResultException("Exception occurred: wrong data provided"));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/currency/current")
                .param("base", currencyExchangeRateModel.getBase())
                .param("target", currencyExchangeRateModel.getTarget()));
        // then - verify the output
        response.andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void givenCurrentCurrencyRate_whenGetCurrentCurrencyRate_thenReturnHistoricalCurrencyRate() throws Exception {
        // given - precondition or setup
        given(currencyExchangeService.getAndProcessCurrentCurrencyRateData(anyString(), anyString(), anyString())).willReturn("100$");

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/currency/historical")
                .param("base", "foo1")
                .param("target", "foo2")
                .param("date", "foo3"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("100$"));
    }

    @Test
    public void givenListOfCurrencyRates_whenGetCurrencyRange_thenReturnListOfAllCurrenciesFoundByBase() throws Exception {
        // given - precondition or setup
        List<CurrencyExchangeRateModelDto> listOfCurrencyExchangeRates = new ArrayList<>();
        CurrencyExchangeRateModelDto currencyExchangeRateModel1 = new CurrencyExchangeRateModelDto(1L, "USD", "PLN", LocalDate.now(), 4.30);
        CurrencyExchangeRateModelDto currencyExchangeRateModel2 = new CurrencyExchangeRateModelDto(2L, "USD", "EUR", LocalDate.now(), 0.95);
        listOfCurrencyExchangeRates.add(currencyExchangeRateModel1);
        listOfCurrencyExchangeRates.add(currencyExchangeRateModel2);
        given(currencyExchangeService.findByBaseCurrency(anyString())).willReturn(listOfCurrencyExchangeRates);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/currency/api/currencies/{base}", "Base"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.size()",
                        is(listOfCurrencyExchangeRates.size())));
    }

    @Test
    public void givenListOfCurrencyRates_whenGetAllCurrencies_thenReturnListOfAllCurrencies() throws Exception {
        // given - precondition or setup
        List<CurrencyExchangeRateModelDto> listOfCurrencyExchangeRates = new ArrayList<>();
        CurrencyExchangeRateModelDto currencyExchangeRateModel1 = new CurrencyExchangeRateModelDto(1L, "USD", "PLN", LocalDate.now(), 4.30);
        CurrencyExchangeRateModelDto currencyExchangeRateModel2 = new CurrencyExchangeRateModelDto(2L, "EUR", "PLN", LocalDate.now(), 4.60);
        listOfCurrencyExchangeRates.add(currencyExchangeRateModel1);
        listOfCurrencyExchangeRates.add(currencyExchangeRateModel2);
        given(currencyExchangeService.getAllCurrenciesData()).willReturn(listOfCurrencyExchangeRates);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/currency/api/currencies/all"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.size()",
                        is(listOfCurrencyExchangeRates.size())));
    }

    @Test
    public void given_whenCount_thenReturnAllCounts() throws Exception {
        // given - precondition or setup
        given(currencyExchangeService.getDBcounter()).willReturn(888L);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/currency/count"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("888"));
    }

}

