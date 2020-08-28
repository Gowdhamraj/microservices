package com.gow.microservices.poc.currencyconversionservice.controller;

import com.gow.microservices.poc.currencyconversionservice.model.CurrencyConversionBean;
import com.gow.microservices.poc.currencyconversionservice.serviceproxy.CurrencyExchangeServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/currency-converter")
public class CurrencyConversionController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @GetMapping("/from/{from}/to/{to}/amount/{amount}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal amount) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate()
                .getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);
        CurrencyConversionBean response = responseEntity.getBody();
        BigDecimal conversionMultiple = response.getConversionMultiple();
        BigDecimal convertedAmount = conversionMultiple.multiply(amount);
        log.info("Currency conversion service response: {}", response);
        log.error("Currency conversion service conversion multiple: {}", conversionMultiple);
        return new CurrencyConversionBean(response.getId(), from, to, conversionMultiple, amount, convertedAmount, response.getPort());
    }

    @GetMapping("/feign/from/{from}/to/{to}/amount/{amount}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal amount) {
        CurrencyConversionBean response = currencyExchangeServiceProxy.getExchangeValue(from, to);
        BigDecimal conversionMultiple = response.getConversionMultiple();
        BigDecimal convertedAmount = conversionMultiple.multiply(amount);
        return new CurrencyConversionBean(response.getId(), from, to, conversionMultiple, amount, convertedAmount, response.getPort());
    }
}
