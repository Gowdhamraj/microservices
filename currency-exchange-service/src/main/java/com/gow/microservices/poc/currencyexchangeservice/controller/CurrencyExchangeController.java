package com.gow.microservices.poc.currencyexchangeservice.controller;

import com.gow.microservices.poc.currencyexchangeservice.repository.ExchangeValueRepository;
import com.gow.microservices.poc.currencyexchangeservice.model.ExchangeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    Environment environment;

    @Autowired
    ExchangeValueRepository exchangeValueRepository;

    @GetMapping(path = "/from/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = exchangeValueRepository.findExchangeValueByFromAndTo(from, to);
        exchangeValue.setPort(environment.getProperty("local.server.port"));
        return exchangeValue;
    }
}
