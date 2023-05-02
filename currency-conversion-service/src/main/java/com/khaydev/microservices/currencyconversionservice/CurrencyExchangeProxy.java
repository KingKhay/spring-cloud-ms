package com.khaydev.microservices.currencyconversionservice;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.math.BigDecimal;

@HttpExchange
public interface CurrencyExchangeProxy {

    @GetExchange("/currency-conversion/feign/from/{from}/to/{to}/quantity/{quantity}")
    CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity);
}
