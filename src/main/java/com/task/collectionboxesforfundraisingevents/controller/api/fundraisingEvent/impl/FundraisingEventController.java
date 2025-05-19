package com.task.collectionboxesforfundraisingevents.controller.api.fundraisingEvent.impl;

import com.task.collectionboxesforfundraisingevents.controller.api.fundraisingEvent.FundraisingEventControllerApi;
import com.task.collectionboxesforfundraisingevents.service.fundraisingEvent.impl.FundraisingEventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FundraisingEventController implements FundraisingEventControllerApi {
    private final FundraisingEventServiceImpl fundraisingEventService;
    @Override
    public ResponseEntity createEvent(String name, String currency) {
        fundraisingEventService.createEvent(name, currency);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String> displayEvents() {
        return ResponseEntity.ok(fundraisingEventService.displayEvents());
    }
}
