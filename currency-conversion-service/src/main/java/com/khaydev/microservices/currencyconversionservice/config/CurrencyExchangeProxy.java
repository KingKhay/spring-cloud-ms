package com.khaydev.microservices.currencyconversionservice.config;

import com.khaydev.microservices.currencyconversionservice.CurrencyConversion;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "http://currency-exchange")
public interface CurrencyExchangeProxy {

    @GetExchange("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion getCurrencyExchange(@PathVariable String from, @PathVariable String to);
}
