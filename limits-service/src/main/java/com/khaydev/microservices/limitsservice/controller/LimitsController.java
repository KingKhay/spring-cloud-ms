package com.khaydev.microservices.limitsservice.controller;

import com.khaydev.microservices.limitsservice.config.ConfigProperties;
import com.khaydev.microservices.limitsservice.model.Limits;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LimitsController {

    private final ConfigProperties configuration;

    @GetMapping("/limits")
    public Limits retrieveLimits(){
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
