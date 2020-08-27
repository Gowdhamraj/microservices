package com.gow.microservices.poc.currencyconversionservice.serviceproxy;

import com.gow.microservices.poc.currencyconversionservice.model.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8001", path = "/currency-exchange")
@FeignClient(name = "currency-exchange-service", path = "/currency-exchange")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping(path = "/from/{from}/to/{to}")
    public CurrencyConversionBean getExchangeValue(@PathVariable(name = "from") String from, @PathVariable(name = "to") String to);
}
