package com.task.collectionboxesforfundraisingevents.service.collectionBox;

import com.task.collectionboxesforfundraisingevents.entity.CollectionBox;

import java.math.BigDecimal;
import java.util.List;

public interface CollectionBoxService {
    void createCollectionBox();

    List<CollectionBox> getAllCollectionBoxes();

    void removeCollectionBox(Integer id);

    void assignCollectionBox(Integer collectionBoxId, Integer fundraisingEventId);

    void addMoneyToCollectionBox(Integer collectionBoxId, String currency, BigDecimal amount);

    void transferMoneyToFundraisingEvent(Integer collectionBoxId, Integer fundraisingEventId);
}
