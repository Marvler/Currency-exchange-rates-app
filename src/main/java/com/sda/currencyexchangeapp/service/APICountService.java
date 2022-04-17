package com.sda.currencyexchangeapp.service;

import com.google.gson.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class APICountService {

    private ApiConnectionService apiConnectionService;
    private final Gson gson;
    private String url = "http://localhost:8081/actuator/metrics/http.server.requests";

    public APICountService(ApiConnectionService apiConnectionService) {
        this.apiConnectionService = apiConnectionService;
        this.gson = new Gson();
    }

    public String getTotalNumberOfApiCalls() {
        ResponseEntity<String> response = apiConnectionService.createApiConnection(url);
        JsonObject jsonObject = gson.fromJson(response.getBody(),JsonObject.class);
        return jsonObject.getAsJsonObject().get("measurements").getAsJsonArray().get(0).getAsJsonObject().get("value").toString();
    }
}
