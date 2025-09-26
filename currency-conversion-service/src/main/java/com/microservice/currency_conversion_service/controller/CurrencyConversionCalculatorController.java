package com.microservice.currency_conversion_service.controller;

import com.microservice.currency_conversion_service.bean.CurrencyConversionBean;
import com.microservice.currency_conversion_service.proxy.CurrencyExchangeServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionCalculatorController {

    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/currency-conversion/from/{from}/to/{to}/{quantity}")
    public CurrencyConversionBean getConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);

        ResponseEntity<CurrencyConversionBean> responseEntity = null;
        try {
            responseEntity =  new RestTemplate().getForEntity(
                    "http://localhost:8001/currency-service/from/{from}/to/{to}",
                    CurrencyConversionBean.class,
                    uriVariables );
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }

        CurrencyConversionBean response = responseEntity.getBody();
        return new CurrencyConversionBean(response.getId(),from,to, quantity,response.getExchangeRate(),
                quantity.multiply(response.getExchangeRate()),response.getPort());
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/{quantity}")
    public CurrencyConversionBean getConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){

        CurrencyConversionBean response = proxy.getCurrencyExchangeRate(from,to);
        logger.info("The response is : {}",response);
        return new CurrencyConversionBean(response.getId(),from,to, quantity,response.getExchangeRate(),
                quantity.multiply(response.getExchangeRate()),response.getPort());
    }
}
