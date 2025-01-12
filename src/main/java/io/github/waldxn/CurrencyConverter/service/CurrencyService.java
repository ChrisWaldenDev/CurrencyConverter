package io.github.waldxn.CurrencyConverter.service;

import io.github.waldxn.CurrencyConverter.config.Config;
import io.github.waldxn.CurrencyConverter.utility.CurrencyUtil;
import io.github.waldxn.CurrencyConverter.utility.DateUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    final Config config;
    final CurrencyUtil currencyUtil;

    public CurrencyService(Config config, CurrencyUtil currencyUtil) {
        this.config = config;
        this.currencyUtil = currencyUtil;
    }

    public String getCurrencyRate(String date, String baseCurrency) {

        if (!DateUtil.isValidDateFormat(date)) {
            return "Invalid Date: " + date;
        }

        if (!currencyUtil.isCurrency(baseCurrency)) {
            return "Invalid Currency: " + baseCurrency;
        }

        String url = config.getApiUrl(date, "currencies/" + baseCurrency + ".json");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    public String getCurrencyRate(String baseCurrency) {

        if (!currencyUtil.isCurrency(baseCurrency)) {
            return "Invalid Currency: " + baseCurrency;
        }

        String url = config.getApiUrl("currencies/" + baseCurrency + ".json");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    public String getCurrencyList(String date) {

        if (!DateUtil.isValidDateFormat(date)) {
            return "Invalid Date: " + date;
        }

        String url = config.getApiUrl(date, "currencies.json");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    public String getCurrencyList() {
        String url = config.getApiUrl("currencies.json");
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Can't fetch currency list");
        }
    }
}
