package com.gow.microservices.poc.currencyconversionservice.serviceproxy;

import com.gow.microservices.poc.currencyconversionservice.model.CurrencyConversionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "currency-exchange-service", url = "localhost:8001", path = "/currency-exchange")
public interface CurrencyExchangeServiceProxy {

    @GetMapping(path = "/from/{from}/to/{to}")
    public CurrencyConversionBean getExchangeValue(@PathVariable(name = "from") String from, @PathVariable(name = "to") String to);
}
