package com.task.collectionboxesforfundraisingevents.service.fundraisingEvent.impl;

import com.task.collectionboxesforfundraisingevents.entity.FundraisingEvent;
import com.task.collectionboxesforfundraisingevents.entity.FundraisingEventAccount;
import com.task.collectionboxesforfundraisingevents.model.Currency;
import com.task.collectionboxesforfundraisingevents.repository.FundraisingEventRepository;
import com.task.collectionboxesforfundraisingevents.service.fundraisingEvent.FundraisingEventService;
import com.task.collectionboxesforfundraisingevents.service.fundraisingEvent.dto.FundraisingEventDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FundraisingEventServiceImpl implements FundraisingEventService {
    private final FundraisingEventRepository fundraisingEventRepository;

    @Transactional
    public FundraisingEventDto createEvent(String name, String currency) {
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");

        if (!Currency.isValid(currency))
            throw new IllegalArgumentException(
                    "Invalid currency: " + currency + ". Currency must be one of: " + Arrays.toString(Currency.values())
            );

        FundraisingEvent fundraisingEvent = new FundraisingEvent();
        fundraisingEvent.setName(name);
        fundraisingEvent.setCurrency(currency);

        FundraisingEventAccount account = new FundraisingEventAccount();

        account.setFundraisingEvent(fundraisingEvent);
        fundraisingEvent.setAccount(account);

        fundraisingEventRepository.save(fundraisingEvent);

        return new FundraisingEventDto(fundraisingEvent);
    }

    @Override
    public List<FundraisingEventDto> displayEvents() {
        return fundraisingEventRepository.findAll().stream().map(FundraisingEventDto::new).collect(Collectors.toList());
    }
}
