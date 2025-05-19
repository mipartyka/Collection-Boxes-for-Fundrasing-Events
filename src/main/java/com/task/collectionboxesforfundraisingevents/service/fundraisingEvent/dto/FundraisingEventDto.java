package com.task.collectionboxesforfundraisingevents.service.fundraisingEvent.dto;

import com.task.collectionboxesforfundraisingevents.entity.FundraisingEvent;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class FundraisingEventDto {
    private Integer id;
    private String name;
    private BigDecimal amount;
    private String currency;

    public FundraisingEventDto(FundraisingEvent fundraisingEvent) {
        this.id = fundraisingEvent.getId();
        this.name = fundraisingEvent.getName();
        this.amount = fundraisingEvent.getAccount().getBalance();
        this.currency = fundraisingEvent.getCurrency();
    }
}
