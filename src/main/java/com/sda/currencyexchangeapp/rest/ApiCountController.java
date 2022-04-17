package com.sda.currencyexchangeapp.rest;

import com.sda.currencyexchangeapp.service.APICountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiCountController {

    private final APICountService apiCountService;

    public ApiCountController(APICountService apiCountService) {
        this.apiCountService = apiCountService;
    }

    @GetMapping("/count")
    public String getTotalNumberApiCalls() {
        return "Number of API calls: " + apiCountService.getTotalNumberOfApiCalls();
    }

}
