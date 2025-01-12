package io.github.waldxn.CurrencyConverter.utility;

import io.github.waldxn.CurrencyConverter.service.CurrencyCacheService;
import org.springframework.stereotype.Component;

@Component
public class CurrencyUtil {

    private final CurrencyCacheService cache;

    CurrencyUtil(CurrencyCacheService cache) {
        this.cache = cache;
    }

    public boolean isCurrency(String currency) {
        return cache.currencyCache.containsKey(currency.toLowerCase());
    }

    public String getCurrencyName(String currency) {
        if (isCurrency(currency)) {
            return cache.currencyCache.get(currency);
        } else {
            return "Invlaid Currency: " + currency;
        }
    }

}
