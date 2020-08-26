package com.gow.microservices.poc.currencyexchangeservice.repository;

import com.gow.microservices.poc.currencyexchangeservice.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    ExchangeValue findExchangeValueByFromAndTo(String from, String to);
}
