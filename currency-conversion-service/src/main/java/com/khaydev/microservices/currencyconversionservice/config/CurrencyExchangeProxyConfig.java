package com.khaydev.microservices.currencyconversionservice.config;

import com.khaydev.microservices.currencyconversionservice.CurrencyExchangeProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class CurrencyExchangeProxyConfig {

    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl("http://currency-exchange")
                .build();
    }

    public CurrencyExchangeProxy httpServiceProxyFactory(){
        HttpServiceProxyFactory build = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient()))
                .build();

        return build.createClient(CurrencyExchangeProxy.class);
    }
}
