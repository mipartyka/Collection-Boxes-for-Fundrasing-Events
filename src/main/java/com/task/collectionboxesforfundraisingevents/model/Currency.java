package com.task.collectionboxesforfundraisingevents.model;

import java.util.Arrays;

public enum Currency {
    PLN,
    EUR,
    USD,
    GBP;

    public static boolean isValid(String currency){
        return Arrays.stream(Currency.values()).anyMatch(e -> e.name().equalsIgnoreCase(currency));
    }
}
