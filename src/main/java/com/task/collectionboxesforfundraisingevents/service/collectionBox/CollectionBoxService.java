package com.task.collectionboxesforfundraisingevents.service.collectionBox;

import com.task.collectionboxesforfundraisingevents.service.collectionBox.dto.CollectionBoxDto;

import java.math.BigDecimal;
import java.util.List;

public interface CollectionBoxService {
    CollectionBoxDto createCollectionBox();

    List<CollectionBoxDto> getAllCollectionBoxes();

    void removeCollectionBox(Integer id);

    void assignCollectionBox(Integer collectionBoxId, Integer fundraisingEventId);

    void addMoneyToCollectionBox(Integer collectionBoxId, String currency, BigDecimal amount);

    void transferMoneyToFundraisingEvent(Integer collectionBoxId);
}
