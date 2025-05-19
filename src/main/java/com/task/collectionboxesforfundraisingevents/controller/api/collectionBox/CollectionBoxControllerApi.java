package com.task.collectionboxesforfundraisingevents.controller.api.collectionBox;

import com.task.collectionboxesforfundraisingevents.service.collectionBox.dto.CollectionBoxDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/collection-box", produces = MediaType.APPLICATION_JSON_VALUE)
public interface CollectionBoxControllerApi {
    @PostMapping(value = "/register")
    ResponseEntity<CollectionBoxDto> registerCollectionBox();

    @GetMapping(value = "/list-all")
    ResponseEntity<List<CollectionBoxDto>> getAllCollectionBoxes();

    @DeleteMapping(value = "/unregister")
    ResponseEntity<Map<String, String>> removeCollectionBox(@RequestParam Integer id);

    @PutMapping(value = "/assign")
    ResponseEntity<Map<String, String>> assignCollectionBox(@RequestParam Integer collectionBoxId, @RequestParam Integer fundraisingEventId);

    @PutMapping(value = "/add-money")
    ResponseEntity<Map<String, String>> addMoneyToCollectionBox(@RequestParam Integer collectionBoxId, @RequestParam String currency, @RequestParam BigDecimal amount);

    @PostMapping(value = "/transfer-to-account")
    ResponseEntity<Map<String, String>> transferMoneyToFundraisingEvent(@RequestParam Integer collectionBoxId);
}
