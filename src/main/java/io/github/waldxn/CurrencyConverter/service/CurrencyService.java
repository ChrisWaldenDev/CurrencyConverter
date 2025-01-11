package io.github.waldxn.CurrencyConverter.service;

import io.github.waldxn.CurrencyConverter.config.Config;
import io.github.waldxn.CurrencyConverter.utility.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    final Config config;

    public CurrencyService(Config config) {
        this.config = config;
    }

    public String getCurrencyRate(String date, String baseCurrency) {

        if (!DateUtil.isValidDateFormat(date)) {
            return "Invalid Date!";
        }

        String url = config.getApiUrl(date, "currencies/" + baseCurrency + ".json");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    public String getCurrencyList(String date) {

        if (!DateUtil.isValidDateFormat(date)) {
            return "Invalid Date!";
        }

        String url = config.getApiUrl(date, "currencies.json");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
