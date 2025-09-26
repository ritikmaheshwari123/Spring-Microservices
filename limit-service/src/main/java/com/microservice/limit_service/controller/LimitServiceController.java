package com.microservice.limit_service.controller;

import com.microservice.limit_service.configuration.Configuration;
import com.microservice.limit_service.model.LimitServiceConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitServiceController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limit-service")
    @HystrixCommand(fallbackMethod = "reliable")
    public LimitServiceConfiguration retrieveLimitsFromConfiguration() {

        return new LimitServiceConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }

    @GetMapping("limit-service-hystrix")
    @HystrixCommand(fallbackMethod = "reliable")
    public LimitServiceConfiguration retievelimitexchangeconfiguration()
    {

        throw new RuntimeException("****Service Error Exception********");
    }

    public LimitServiceConfiguration reliable() {
        return new LimitServiceConfiguration(1,1);
    }


}
