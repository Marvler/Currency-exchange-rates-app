package com.sda.currencyexchangeapp.service.API;

import com.google.gson.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class APICountService {

    private final APIConnectionService apiConnectionService;
    private final Gson gson;

    public APICountService(APIConnectionService apiConnectionService) {
        this.apiConnectionService = apiConnectionService;
        this.gson = new Gson();
    }

    public String getTotalNumberOfApiCalls(String endpoint) {
        String url = "http://localhost:8081/actuator/metrics/http.server.requests?tag=uri:";
        ResponseEntity<String> response = apiConnectionService.createApiConnection(url + endpoint);
        JsonObject jsonObject = gson.fromJson(response.getBody(), JsonObject.class);
        return jsonObject.getAsJsonObject().get("measurements").getAsJsonArray().get(0).getAsJsonObject().get("value").toString();

    }
}