package com.task.collectionboxesforfundraisingevents.service.fundraisingEvent;

import com.task.collectionboxesforfundraisingevents.service.fundraisingEvent.dto.FundraisingEventDto;

import java.util.List;

public interface FundraisingEventService {
    FundraisingEventDto createEvent(String name, String currency);

    List<FundraisingEventDto> displayEvents();
}
