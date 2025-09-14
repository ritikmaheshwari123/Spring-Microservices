package com.microservice.currency_exchange.repo;

import com.microservice.currency_exchange.entity.CurrencyExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRateRepo extends JpaRepository<CurrencyExchangeRate,Integer> {

    //Query Method
    CurrencyExchangeRate findByFromAndTo(String from, String to);
}
