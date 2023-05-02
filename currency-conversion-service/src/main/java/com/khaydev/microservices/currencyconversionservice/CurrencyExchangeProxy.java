package com.khaydev.microservices.currencyconversionservice;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface CurrencyExchangeProxy {

    @GetExchange("/currency-exchange/from/{from}/to/{to}")
    CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
