package com.sda.currencyexchangeapp.service.validator;

import com.sda.currencyexchangeapp.model.gold.GoldProcessingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class Validation {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

    public boolean validateIfCurrencyExists(String base, String target) {

        Set<String> currency = new HashSet<>();
        JSONParser jsonParser = new JSONParser();

        try {
            Object object = jsonParser.parse(new FileReader("src/main/resources/currency.json"));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray currencyListFromJSON = (JSONArray) jsonObject.get("currency");
            for (Object o : currencyListFromJSON) {
                currency.add(String.valueOf(o));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return currency.contains(base) && currency.contains(target);

    }

    public void validateDateFormat(String... dates) {
        for (String date : dates) {
            try {
                LocalDate.parse(date, formatter);
            } catch (Exception e) {
                throw new GoldProcessingException("Wrong format date!");
            }
        }
    }

}

