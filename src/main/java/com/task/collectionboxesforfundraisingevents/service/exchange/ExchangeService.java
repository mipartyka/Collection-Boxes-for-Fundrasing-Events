package com.task.collectionboxesforfundraisingevents.service.exchange;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public interface ExchangeService {
    Map<String, BigDecimal> getExchangeRates(String sourceCurrency, Set<String> targetCurrencies);
}
