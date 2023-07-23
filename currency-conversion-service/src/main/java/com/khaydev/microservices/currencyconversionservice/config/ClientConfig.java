package com.khaydev.microservices.currencyconversionservice.config;

import com.khaydev.microservices.currencyconversionservice.CurrencyExchangeProxy;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {

    private final LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

    public ClientConfig(LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction) {
        this.loadBalancedExchangeFilterFunction = loadBalancedExchangeFilterFunction;
    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .filter(loadBalancedExchangeFilterFunction)
                .build();
    }

    @Bean
    public CurrencyExchangeProxy currencyExchangeProxy(){
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient()))
                .build();

        return httpServiceProxyFactory.createClient(CurrencyExchangeProxy.class);
    }
}
