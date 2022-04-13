package com.sda.currencyexchangeapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiConnectionService {

    public ResponseEntity<String> createApiConnection(String url){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url,String.class);
    }
}
