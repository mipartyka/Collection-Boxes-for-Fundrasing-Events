package com.task.collectionboxesforfundraisingevents.controller.api.collectionBox.impl;

import com.task.collectionboxesforfundraisingevents.controller.api.collectionBox.CollectionBoxControllerApi;
import com.task.collectionboxesforfundraisingevents.service.collectionBox.dto.CollectionBoxDto;
import com.task.collectionboxesforfundraisingevents.service.collectionBox.impl.CollectionBoxServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CollectionBoxController implements CollectionBoxControllerApi {
    private final CollectionBoxServiceImpl collectionBoxService;

    @Override
    public ResponseEntity<CollectionBoxDto> registerCollectionBox() {
        return ResponseEntity.ok(collectionBoxService.createCollectionBox());
    }

    @Override
    public ResponseEntity<List<CollectionBoxDto>> getAllCollectionBoxes() {
        return ResponseEntity.ok(collectionBoxService.getAllCollectionBoxes());
    }

    @Override
    public ResponseEntity<Map<String, String>> removeCollectionBox(Integer id) {
        collectionBoxService.removeCollectionBox(id);
        return ResponseEntity.ok(Map.of("message", "Collection box unregistered successfully."));
    }

    @Override
    public ResponseEntity<Map<String, String>> assignCollectionBox(Integer collectionBoxId, Integer fundraisingEventId) {
        collectionBoxService.assignCollectionBox(collectionBoxId, fundraisingEventId);
        return ResponseEntity.ok(Map.of("message", "Collection box assigned successfully."));
    }

    @Override
    public ResponseEntity<Map<String, String>> addMoneyToCollectionBox(Integer collectionBoxId, String currency, BigDecimal amount) {
        collectionBoxService.addMoneyToCollectionBox(collectionBoxId, currency, amount);
        return ResponseEntity.ok(Map.of("message", "Money added to collection box successfully."));
    }

    @Override
    public ResponseEntity<Map<String, String>> transferMoneyToFundraisingEvent(Integer collectionBoxId) {
        collectionBoxService.transferMoneyToFundraisingEvent(collectionBoxId);
        return ResponseEntity.ok(Map.of("message", "Money transferred to fundraising event successfully."));
    }
}
