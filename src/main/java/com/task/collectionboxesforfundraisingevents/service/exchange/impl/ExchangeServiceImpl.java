package com.task.collectionboxesforfundraisingevents.service.exchange.impl;

import com.task.collectionboxesforfundraisingevents.service.exchange.ExchangeService;
import com.task.collectionboxesforfundraisingevents.service.exchange.dto.ExchangeResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    private final RestTemplate restTemplate;

    @Value("${exchange.api.key}")
    private String key;

    public ExchangeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getExchangeRate(String inputCurrency, String outputCurrency) {
        String url = String.format(
                "https://api.exchangerate.host/live?access_key=%s&source=%s&currencies=%s",
                key, inputCurrency.toUpperCase(), outputCurrency.toUpperCase());

        ResponseEntity<ExchangeResponseDto> response = restTemplate.getForEntity(url, ExchangeResponseDto.class);

        if(response.getStatusCode().is2xxSuccessful() && !Objects.isNull(response.getBody()))
            return response.getBody().getQuotes().get(inputCurrency.toUpperCase() + outputCurrency.toUpperCase());
        else
            throw new RuntimeException("Failed to fetch exchange rate");
    }
}
