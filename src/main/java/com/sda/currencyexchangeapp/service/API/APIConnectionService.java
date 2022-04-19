package com.sda.currencyexchangeapp.service.API;

import com.sda.currencyexchangeapp.model.gold.GoldProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class APIConnectionService {

    public ResponseEntity<String> createApiConnection(String url) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException exception) {
            throw new GoldProcessingException("There are no records");
        }
    }
}
