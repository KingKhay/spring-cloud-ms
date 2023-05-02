package com.khaydev.microservices.currencyconversionservice;

import java.math.BigDecimal;

public record CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple, String environment) {
}
