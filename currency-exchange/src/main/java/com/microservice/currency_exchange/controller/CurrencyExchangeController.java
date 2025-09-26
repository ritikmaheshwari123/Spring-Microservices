package com.microservice.currency_exchange.controller;

import com.microservice.currency_exchange.entity.CurrencyExchangeRate;
import com.microservice.currency_exchange.repo.CurrencyExchangeRateRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Environment environment;

    @Autowired
    CurrencyExchangeRateRepo currencyExchangeRateRepo;

    @GetMapping("/currency-service/from/{from}/to/{to}")
    public CurrencyExchangeRate getCurrencyExchangeRate(@PathVariable String from, @PathVariable String to){

        CurrencyExchangeRate currencyExchangeRate = currencyExchangeRateRepo.findByFromAndTo(from,to);
        currencyExchangeRate.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        logger.info("The currency exchange rate is : {}",currencyExchangeRate);
        return currencyExchangeRate;

    }

}
