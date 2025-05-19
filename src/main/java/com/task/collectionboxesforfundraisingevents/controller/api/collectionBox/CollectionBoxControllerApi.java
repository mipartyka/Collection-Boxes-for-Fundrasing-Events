package com.task.collectionboxesforfundraisingevents.controller.api.collectionBox;

import com.task.collectionboxesforfundraisingevents.service.collectionBox.dto.CollectionBoxDto;
import jakarta.validation.constraints.NotNull;
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
    ResponseEntity<Map<String, String>> removeCollectionBox(@RequestParam @NotNull Integer id);

    @PutMapping(value = "/assign")
    ResponseEntity<Map<String, String>> assignCollectionBox(@RequestParam @NotNull Integer collectionBoxId, @RequestParam @NotNull Integer fundraisingEventId);

    @PutMapping(value = "/add-money")
    ResponseEntity<Map<String, String>> addMoneyToCollectionBox(@RequestParam @NotNull Integer collectionBoxId, @RequestParam @NotNull String currency, @RequestParam BigDecimal amount);

    @PostMapping(value = "/transfer-to-account")
    ResponseEntity<Map<String, String>> transferMoneyToFundraisingEvent(@RequestParam @NotNull Integer collectionBoxId);
}
