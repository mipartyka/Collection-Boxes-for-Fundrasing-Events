package com.task.collectionboxesforfundraisingevents.controller.api.collectionBox.impl;

import com.task.collectionboxesforfundraisingevents.controller.api.collectionBox.CollectionBoxControllerApi;
import com.task.collectionboxesforfundraisingevents.entity.CollectionBox;
import com.task.collectionboxesforfundraisingevents.service.collectionBox.impl.CollectionBoxServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CollectionBoxController implements CollectionBoxControllerApi {
    private final CollectionBoxServiceImpl collectionBoxService;

    @Override
    public ResponseEntity registerCollectionBox() {
        collectionBoxService.createCollectionBox();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CollectionBox>> getAllCollectionBoxes() {
        return ResponseEntity.ok(collectionBoxService.getAllCollectionBoxes());
    }

    @Override
    public ResponseEntity removeCollectionBox(Integer id) {
        collectionBoxService.removeCollectionBox(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity assignCollectionBox(Integer collectionBoxId, Integer fundraisingEventId) {
        collectionBoxService.assignCollectionBox(collectionBoxId, fundraisingEventId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity addMoneyToCollectionBox(Integer collectionBoxId, String currency, BigDecimal amount) {
        collectionBoxService.addMoneyToCollectionBox(collectionBoxId, currency, amount);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity transferMoneyToFundraisingEvent(Integer collectionBoxId, Integer FundraisingEventId) {
        collectionBoxService.transferMoneyToFundraisingEvent(collectionBoxId, FundraisingEventId);
        return ResponseEntity.ok().build();
    }
}
