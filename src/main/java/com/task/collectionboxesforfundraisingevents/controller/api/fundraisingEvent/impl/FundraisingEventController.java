package com.task.collectionboxesforfundraisingevents.controller.api.fundraisingEvent.impl;

import com.task.collectionboxesforfundraisingevents.controller.api.fundraisingEvent.FundraisingEventControllerApi;
import com.task.collectionboxesforfundraisingevents.service.fundraisingEvent.dto.FundraisingEventDto;
import com.task.collectionboxesforfundraisingevents.service.fundraisingEvent.impl.FundraisingEventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FundraisingEventController implements FundraisingEventControllerApi {
    private final FundraisingEventServiceImpl fundraisingEventService;
    @Override
    public ResponseEntity<FundraisingEventDto> createEvent(String name, String currency) {
        return ResponseEntity.ok(fundraisingEventService.createEvent(name, currency));
    }

    @Override
    public ResponseEntity<List<FundraisingEventDto>> displayEvents() {
        return ResponseEntity.ok(fundraisingEventService.displayEvents());
    }
}
