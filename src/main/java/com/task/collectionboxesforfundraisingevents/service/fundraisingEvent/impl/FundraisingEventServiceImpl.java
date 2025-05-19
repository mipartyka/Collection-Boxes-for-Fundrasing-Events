package com.task.collectionboxesforfundraisingevents.service.fundraisingEvent.impl;

import com.task.collectionboxesforfundraisingevents.entity.FundraisingEvent;
import com.task.collectionboxesforfundraisingevents.entity.FundraisingEventAccount;
import com.task.collectionboxesforfundraisingevents.repository.FundraisingEventAccountRepository;
import com.task.collectionboxesforfundraisingevents.repository.FundraisingEventRepository;
import com.task.collectionboxesforfundraisingevents.service.fundraisingEvent.FundraisingEventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FundraisingEventServiceImpl implements FundraisingEventService {
    private final FundraisingEventRepository fundraisingEventRepository;
    private final FundraisingEventAccountRepository fundraisingEventAccountRepository;

    @Transactional
    public void createEvent(String name, String currency) {
        FundraisingEvent fundraisingEvent = new FundraisingEvent();
        fundraisingEvent.setName(name);
        fundraisingEvent.setCurrency(currency);

        FundraisingEventAccount account = new FundraisingEventAccount();

        account.setFundraisingEvent(fundraisingEvent);
        fundraisingEvent.setAccount(account);

        fundraisingEventRepository.save(fundraisingEvent);
    }

    @Override
    public String displayEvents() {
        return fundraisingEventRepository.findAll().toString();
    }
}
