package com.task.collectionboxesforfundraisingevents.controller.api.collectionBox;

import com.task.collectionboxesforfundraisingevents.entity.CollectionBox;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping(value = "/collection-box", produces = MediaType.APPLICATION_JSON_VALUE)

public interface CollectionBoxControllerApi {
    @PostMapping(value = "/register")
    ResponseEntity registerCollectionBox();

    @GetMapping(value = "/list-all")
    ResponseEntity<List<CollectionBox>> getAllCollectionBoxes();

    @DeleteMapping(value = "/unregister")
    ResponseEntity removeCollectionBox(@RequestParam Integer id);

    @PostMapping(value = "/assign")
    ResponseEntity assignCollectionBox(@RequestParam Integer collectionBoxId, @RequestParam Integer fundraisingEventId);

    @PutMapping(value = "/add-money")
    ResponseEntity addMoneyToCollectionBox(@RequestParam Integer collectionBoxId, @RequestParam String currency, @RequestParam BigDecimal amount);

    @PostMapping(value = "/transfer-to-account")
    ResponseEntity transferMoneyToFundraisingEvent(@RequestParam Integer collectionBoxId, @RequestParam Integer FundraisingEventId);
}
