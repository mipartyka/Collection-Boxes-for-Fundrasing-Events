package com.task.collectionboxesforfundraisingevents.controller.api.fundraisingEvent;

import com.task.collectionboxesforfundraisingevents.service.fundraisingEvent.dto.FundraisingEventDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "/event", produces = MediaType.APPLICATION_JSON_VALUE)
public interface FundraisingEventControllerApi {
    @PostMapping(value = "/create")
    ResponseEntity<FundraisingEventDto> createEvent(@RequestParam @NotNull String name, @RequestParam @NotNull String currency);

    @GetMapping(value = "/display")
    ResponseEntity<List<FundraisingEventDto>> displayEvents();
}
