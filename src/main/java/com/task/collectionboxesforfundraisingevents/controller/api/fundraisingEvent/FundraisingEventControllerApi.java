package com.task.collectionboxesforfundraisingevents.controller.api.fundraisingEvent;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/event", produces = MediaType.APPLICATION_JSON_VALUE)
public interface FundraisingEventControllerApi {
    @PostMapping(value = "/create")
    ResponseEntity createEvent(@RequestParam String name, @RequestParam String currency);

    @GetMapping(value = "/display")
    ResponseEntity<String> displayEvents();
}
