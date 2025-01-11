package io.github.waldxn.CurrencyConverter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {

    @Value("${currency-api.base-url}")
    private String baseUrl;

    @Value("${currency-api.default-date}")
    private String defaultDate;

    @Value("${currency-api.api-version}")
    private String apiVersion;

    @Value("${currency-api.fallback-url}")
    private String fallbackUrl;

    public String getApiUrl(String endpoint) {
        return String.format("%s@%s/%s/%s", baseUrl, defaultDate, apiVersion, endpoint);
    }

    public String getApiUrl(String date, String endpoint) {
        return String.format("%s@%s/%s/%s", baseUrl, date, apiVersion, endpoint);
    }

    public String getFallbackUrl(String endpoint) {
        return String.format("%s.%s/%s/%s", defaultDate, fallbackUrl, apiVersion, endpoint);
    }

    public String getFallbackUrl(String date, String endpoint) {
        return String.format("%s.%s/%s/%s", date, fallbackUrl, apiVersion, endpoint);
    }
}
