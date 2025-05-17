package com.task.collectionboxesforfundraisingevents.service.fundraisingEvent.impl;

import com.task.collectionboxesforfundraisingevents.service.fundraisingEvent.FundraisingEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FundraisingEventServiceImpl implements FundraisingEventService {
    public void createEvent(String name, String currency) {
    }

    @Override
    public String displayEvents() {
        return null;
    }
}
