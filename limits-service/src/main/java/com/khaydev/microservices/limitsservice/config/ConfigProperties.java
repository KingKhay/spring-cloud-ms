package com.khaydev.microservices.limitsservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "limits-service")
@Data
public class ConfigProperties {

    private int minimum;
    private int maximum;
}
