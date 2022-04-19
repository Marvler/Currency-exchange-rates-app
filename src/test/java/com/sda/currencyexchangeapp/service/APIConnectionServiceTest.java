package com.sda.currencyexchangeapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class APIConnectionServiceTest {

    private final ApiConnectionService apiConnectionService;

    @Autowired
    public APIConnectionServiceTest(ApiConnectionService apiConnectionService) {
        this.apiConnectionService = apiConnectionService;
    }

    @Test
    public void shouldCheckIfConnectionIsEstablished() {

        ResponseEntity<String> response = apiConnectionService.createApiConnection("https://api.exchangerate.host/latest?base=PLN&symbols=USD");
        assertThat(response).isNotNull();
    }

}
