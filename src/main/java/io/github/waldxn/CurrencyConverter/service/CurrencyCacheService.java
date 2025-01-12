package io.github.waldxn.CurrencyConverter.service;

import io.github.waldxn.CurrencyConverter.config.Config;
import jakarta.annotation.PostConstruct;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CurrencyCacheService {

    public ConcurrentHashMap<String, String> currencyCache;
    private final Config config;

    CurrencyCacheService(Config config) {
        this.config = config;
    }

    @PostConstruct
    void initializeCache() {
        currencyCache = new ConcurrentHashMap<>();

        String url = config.getApiUrl("currencies.json");
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> currencies = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Map<String, String>>() {}).getBody();

        if (currencies != null) currencyCache.putAll(currencies);
    }
}
