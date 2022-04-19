package com.sda.currencyexchangeapp.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "properties")
@Data
public class ConfigProperties {

        private String goldUrl = "http://api.nbp.pl/api/cenyzlota/";
        private String currencyUrl ="https://api.exchangerate.host/latest?base=%s&symbols=%s";
        private String currencyWithDateUrl ="https://api.exchangerate.host/%s?base=%s&symbols=%s";
        private Long port = 8081L;
}
