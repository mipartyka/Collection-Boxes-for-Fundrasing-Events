package com.task.collectionboxesforfundraisingevents.service.collectionBox.impl;

import com.task.collectionboxesforfundraisingevents.entity.CollectionBox;
import com.task.collectionboxesforfundraisingevents.service.collectionBox.CollectionBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionBoxServiceImpl implements CollectionBoxService {
    public void registerCollectionBox() {
    }

    @Override
    public List<CollectionBox> getAllCollectionBoxes() {
        return null;
    }

    @Override
    public void removeCollectionBox(Integer id) {

    }

    @Override
    public void assignCollectionBox(Integer collectionBoxId, Integer fundraisingEventId) {

    }

    @Override
    public void addMoneyToCollectionBox(Integer collectionBoxId, String currency, BigDecimal amount) {

    }

    @Override
    public void transferMoneyToFundraisingEvent(Integer collectionBoxId, Integer fundraisingEventId) {

    }
}
