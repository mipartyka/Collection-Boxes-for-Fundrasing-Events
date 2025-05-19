package com.task.collectionboxesforfundraisingevents.service.collectionBox.impl;

import com.task.collectionboxesforfundraisingevents.entity.CollectionBox;
import com.task.collectionboxesforfundraisingevents.entity.CollectionBoxContent;
import com.task.collectionboxesforfundraisingevents.entity.FundraisingEvent;
import com.task.collectionboxesforfundraisingevents.entity.FundraisingEventAccount;
import com.task.collectionboxesforfundraisingevents.repository.CollectionBoxContentRepository;
import com.task.collectionboxesforfundraisingevents.repository.CollectionBoxRepository;
import com.task.collectionboxesforfundraisingevents.repository.FundraisingEventAccountRepository;
import com.task.collectionboxesforfundraisingevents.repository.FundraisingEventRepository;
import com.task.collectionboxesforfundraisingevents.service.collectionBox.CollectionBoxService;
import com.task.collectionboxesforfundraisingevents.service.collectionBox.dto.CollectionBoxDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectionBoxServiceImpl implements CollectionBoxService {
    private final CollectionBoxRepository collectionBoxRepository;
    private final FundraisingEventRepository fundraisingEventRepository;
    private final CollectionBoxContentRepository collectionBoxContentRepository;
    private final FundraisingEventAccountRepository fundraisingEventAccountRepository;

    public CollectionBoxDto createCollectionBox() {
        return new CollectionBoxDto(collectionBoxRepository.save(new CollectionBox()));
    }

    @Override
    public List<CollectionBoxDto> getAllCollectionBoxes() {
        return collectionBoxRepository.findAll().stream().map(CollectionBoxDto::new).collect(Collectors.toList());
    }

    @Override
    public void removeCollectionBox(Integer id) {
        collectionBoxRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void assignCollectionBox(Integer collectionBoxId, Integer fundraisingEventId) {
        CollectionBox box = collectionBoxRepository.findById(collectionBoxId)
                .orElseThrow(() -> new EntityNotFoundException("Collection box not found"));

        if (!box.isEmpty())
            throw new IllegalStateException("Collection box must be empty before assigning");

        FundraisingEvent event = fundraisingEventRepository.findById(fundraisingEventId)
                .orElseThrow(() -> new EntityNotFoundException("Fundraising event not found"));

        box.setFundraisingEvent(event);
        box.setAssigned(true);
        collectionBoxRepository.save(box);
    }

    @Override
    @Transactional
    public void addMoneyToCollectionBox(Integer collectionBoxId, String currency, BigDecimal amount) {
        CollectionBox box = collectionBoxRepository.findById(collectionBoxId)
                .orElseThrow(() -> new EntityNotFoundException("Collection box not found"));

        Optional<CollectionBoxContent> optionalContent = collectionBoxContentRepository.findByCollectionBoxIdAndCurrency(collectionBoxId, currency);

        CollectionBoxContent content = optionalContent.orElseGet(() -> {
            CollectionBoxContent newContent = new CollectionBoxContent();
            newContent.setCollectionBox(box);
            newContent.setCurrency(currency);
            newContent.setAmount(BigDecimal.ZERO);
            box.setIsEmpty(false);
            collectionBoxRepository.save(box);
            return newContent;
        });

        box.setIsEmpty(false);
        content.setAmount(content.getAmount().add(amount));
        collectionBoxContentRepository.save(content);
    }

    @Override
    @Transactional
    public void transferMoneyToFundraisingEvent(Integer collectionBoxId) {
        CollectionBox box = collectionBoxRepository.findById(collectionBoxId)
                .orElseThrow(() -> new EntityNotFoundException("Collection box not found"));

        if (box.getFundraisingEvent() == null)
            throw new IllegalStateException("Collection box is not assigned to any fundraising event");

        FundraisingEvent event = box.getFundraisingEvent();
        FundraisingEventAccount account = event.getAccount();

        List<CollectionBoxContent> contentList = collectionBoxContentRepository
                .findByCollectionBoxId(collectionBoxId)
                .orElseThrow(() -> new EntityNotFoundException("No contents found for collection box"));

        contentList.forEach(content -> account.setBalance(account.getBalance()
                .add(calculateExchange(content.getAmount(), content.getCurrency(), event.getCurrency()))));

        collectionBoxContentRepository.deleteAll(contentList);
        box.setIsEmpty(true);
        collectionBoxRepository.save(box);
        fundraisingEventAccountRepository.save(account);
    }

    private BigDecimal calculateExchange(BigDecimal amount, String inputCurrency, String outputCurrency) {
        if(Objects.equals(inputCurrency, outputCurrency))
            return amount;

        // Hardcoded for EUR as base TODO: online API with exchange rates
        Map<String, BigDecimal> toEUR = Map.of(
                "EUR", BigDecimal.valueOf(1.0),
                "USD", BigDecimal.valueOf(0.85),
                "GBP", BigDecimal.valueOf(1.15)
        );

        BigDecimal amountInEUR = amount.divide(toEUR.get(inputCurrency), 4, RoundingMode.HALF_UP);
        BigDecimal outputRate = toEUR.get(outputCurrency);

        return amountInEUR.multiply(outputRate).setScale(2, RoundingMode.HALF_UP);
    }
}
