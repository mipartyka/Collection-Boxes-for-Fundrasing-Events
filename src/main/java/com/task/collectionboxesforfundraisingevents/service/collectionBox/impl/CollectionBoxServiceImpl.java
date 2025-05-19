package com.task.collectionboxesforfundraisingevents.service.collectionBox.impl;

import com.task.collectionboxesforfundraisingevents.entity.CollectionBox;
import com.task.collectionboxesforfundraisingevents.repository.CollectionBoxContentRepository;
import com.task.collectionboxesforfundraisingevents.repository.CollectionBoxRepository;
import com.task.collectionboxesforfundraisingevents.repository.FundraisingEventRepository;
import com.task.collectionboxesforfundraisingevents.service.collectionBox.CollectionBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionBoxServiceImpl implements CollectionBoxService {
    private final CollectionBoxRepository collectionBoxRepository;
    private final FundraisingEventRepository fundraisingEventRepository;
    private final CollectionBoxContentRepository collectionBoxContentRepository;

    public void createCollectionBox() {
        collectionBoxRepository.save(new CollectionBox());
    }

    @Override
    public List<CollectionBox> getAllCollectionBoxes() {
        return collectionBoxRepository.findAll();
    }

    @Override
    public void removeCollectionBox(Integer id) {
        collectionBoxRepository.deleteById(id);
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
