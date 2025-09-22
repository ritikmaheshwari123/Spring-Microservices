package com.microservice.currency_conversion_service.proxy;

import com.microservice.currency_conversion_service.bean.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="exchange-service", url="localhost:8001")
@FeignClient(name="exchange-service")
@RibbonClient(name="exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-service/from/{from}/to/{to}")
    public CurrencyConversionBean getCurrencyExchangeRate
            (@PathVariable String from, @PathVariable String to);
}