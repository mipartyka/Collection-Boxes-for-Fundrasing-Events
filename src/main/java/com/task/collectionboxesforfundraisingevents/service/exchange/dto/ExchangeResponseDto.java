package com.task.collectionboxesforfundraisingevents.service.exchange.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class ExchangeResponseDto {
    private Map<String, BigDecimal> quotes;
}
