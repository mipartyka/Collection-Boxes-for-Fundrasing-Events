package com.task.collectionboxesforfundraisingevents.service.fundraisingEvent;

public interface FundraisingEventService {
    void createEvent(String name, String currency);

    String displayEvents();
}
