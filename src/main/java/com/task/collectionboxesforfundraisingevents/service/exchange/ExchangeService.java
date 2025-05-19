package com.task.collectionboxesforfundraisingevents.service.exchange;

import java.math.BigDecimal;

public interface ExchangeService {
    BigDecimal getExchangeRate(String inputCurrency, String outputCurrency);
}
