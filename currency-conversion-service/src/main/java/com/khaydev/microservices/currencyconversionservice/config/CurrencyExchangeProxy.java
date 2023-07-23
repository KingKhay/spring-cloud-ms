package com.khaydev.microservices.currencyconversionservice.config;

import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "http://currency-exchange")
public interface CurrencyExchangeProxy {
}
