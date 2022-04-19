package com.sda.currencyexchangeapp.service.API;

import com.google.gson.*;
import com.sda.currencyexchangeapp.configuration.ConfigProperties;
import com.sda.currencyexchangeapp.configuration.GsonConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class APICountService {

    private final APIConnectionService apiConnectionService;
    private final Gson gson;
    private final ConfigProperties configProperties;

    public APICountService(APIConnectionService apiConnectionService, GsonConfig gson, ConfigProperties configProperties) {
        this.apiConnectionService = apiConnectionService;
        this.gson = gson.gson();
        this.configProperties = configProperties;
    }

    public String getTotalNumberOfApiCalls(String endpoint) {
        String url = "http://localhost:" + configProperties.getPort() + "/actuator/metrics/http.server.requests?tag=uri:";
        ResponseEntity<String> response = apiConnectionService.createApiConnection(url + endpoint);
        JsonObject jsonObject = gson.fromJson(response.getBody(), JsonObject.class);
        return jsonObject.getAsJsonObject().get("measurements").getAsJsonArray().get(0).getAsJsonObject().get("value").toString();

    }
}
