package com.sda.currencyexchangeapp.service.API;

import com.sda.currencyexchangeapp.model.gold.NoResultException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class APIConnectionService {

//   private final RestTemplate restTemplate;
//
//    public APIConnectionService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }


    public ResponseEntity<String> createApiConnection(String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException exception) {
            throw new NoResultException("There are no records");
        }
    }
}
