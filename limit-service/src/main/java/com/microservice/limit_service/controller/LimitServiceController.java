package com.microservice.limit_service.controller;

import com.microservice.limit_service.configuration.Configuration;
import com.microservice.limit_service.model.LimitServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitServiceController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limit-service")
    public LimitServiceConfiguration retrieveLimitsFromConfiguration() {

        return new LimitServiceConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }
}
