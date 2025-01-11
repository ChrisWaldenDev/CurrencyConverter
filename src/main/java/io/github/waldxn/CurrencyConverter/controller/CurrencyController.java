package io.github.waldxn.CurrencyConverter.controller;

import io.github.waldxn.CurrencyConverter.service.CurrencyService;
import io.github.waldxn.CurrencyConverter.utility.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CurrencyController {

    final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currencies/{date}")
    String getCurrencyList(@PathVariable String date) {
        return currencyService.getCurrencyList(date);
    }
}
