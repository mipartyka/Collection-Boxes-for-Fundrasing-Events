package com.task.collectionboxesforfundraisingevents.service.exchange.impl;

import com.task.collectionboxesforfundraisingevents.service.exchange.ExchangeService;
import com.task.collectionboxesforfundraisingevents.service.exchange.dto.ExchangeResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    private final RestTemplate restTemplate;

    @Value("${exchange.api.key}")
    private String apiKey;

    public ExchangeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Map<String, BigDecimal> getExchangeRates(String sourceCurrency, Set<String> targetCurrencies) {
        String url = String.format(
                "https://api.exchangerate.host/live?access_key=%s&source=%s&currencies=%s",
                apiKey, sourceCurrency.toUpperCase(), String.join(",", targetCurrencies).toUpperCase()
        );

        ResponseEntity<ExchangeResponseDto> response = restTemplate.getForEntity(url, ExchangeResponseDto.class);

        if (response.getStatusCode().is2xxSuccessful() && !Objects.isNull( response.getBody()))
            return response.getBody().getQuotes();
        else
            throw new RuntimeException("Failed to fetch exchange rates. Status code: " + response.getStatusCode());
    }
}