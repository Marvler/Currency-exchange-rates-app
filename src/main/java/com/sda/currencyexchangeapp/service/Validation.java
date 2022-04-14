package com.sda.currencyexchangeapp.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class Validation {

    public boolean validateIfCurrencyExists(String base, String target) {

        Set<String> currency = new HashSet<>();
        JSONParser jsonParser = new JSONParser();

        try {
            Object object = jsonParser.parse(new FileReader("src/main/resources/currency.json"));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray currencyListFromJSON = (JSONArray) jsonObject.get("currency");
            Iterator<JSONObject> iterator = currencyListFromJSON.iterator();
            while (iterator.hasNext()) {
                currency.add(String.valueOf(iterator.next()));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return currency.contains(base) && currency.contains(target);

    }

}
