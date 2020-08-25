package com.gow.microservices.poc.limitsservice.controller;

import com.gow.microservices.poc.limitsservice.configuration.Configuration;
import com.gow.microservices.poc.limitsservice.model.LimitsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LimitServiceController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitsConfiguration getLimitsFromConfiguration()
    {
        return new LimitsConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }
    
}
