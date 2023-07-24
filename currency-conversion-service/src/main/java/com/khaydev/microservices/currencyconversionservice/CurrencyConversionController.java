package com.khaydev.microservices.currencyconversionservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy proxy;

    private final static String currency_conversion = "currency_conversion";

    private final Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    @CircuitBreaker(name= currency_conversion, fallbackMethod = "fallbackForCurrencyConversion")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, uriVariables);


        CurrencyConversion body = response.getBody();

        if(body == null){
            return new CurrencyConversion();
        }

        return new CurrencyConversion(
                body.getId(),
                from,
                to,
                quantity,
                body.getConversionMultiple(),
                quantity.multiply(body.getConversionMultiple()),
                body.getEnvironment());
    }


    @GetMapping("/currency-conversion/feign/from/{from}/to/{to}/quantity/{quantity}")
    @Retry(name = "currency_conversion_retry", fallbackMethod = "handleFallBack")
    @RateLimiter(name = "currency_conversion")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        logger.info("Calculating currency conversion");
        CurrencyExchange currencyExchange = proxy.retrieveExchangeValue(from, to);
        return new CurrencyConversion(currencyExchange.id(),
                from,
                to,
                quantity,
                currencyExchange.conversionMultiple(),
                quantity.multiply(currencyExchange.conversionMultiple()),
                currencyExchange.environment() + " feign");
    }

    public CurrencyConversion handleFallBack(Exception ex){
        return new CurrencyConversion(0L,"TBC","TBC",BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,"fallback");
    }

    public CurrencyConversion fallbackForCurrencyConversion(Exception exception){
        return new CurrencyConversion(0L, "Non", "Non", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, "fallback");
    }
}
