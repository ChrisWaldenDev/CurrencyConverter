package io.github.waldxn.CurrencyConverter.controller;

import io.github.waldxn.CurrencyConverter.service.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currencies/{date}")
    String getCurrencyList(@PathVariable String date) {
        return currencyService.getCurrencyList(date);
    }

    @GetMapping("/currencies")
    String getCurrencyListLatest() {
        return currencyService.getCurrencyList();
    }

    @GetMapping("/currency/{currencyCode}/{date}")
    String getCurrencyRate(@PathVariable String currencyCode,
                           @PathVariable String date) {
        return currencyService.getCurrencyRate(date, currencyCode);
    }

    @GetMapping("/currency/{currencyCode}")
    String getCurrencyRateLatest(@PathVariable String currencyCode) {
        return currencyService.getCurrencyRate(currencyCode);
    }
}
