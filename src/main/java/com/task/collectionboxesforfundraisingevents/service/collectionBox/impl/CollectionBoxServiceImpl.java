package com.task.collectionboxesforfundraisingevents.service.collectionBox.impl;

import com.task.collectionboxesforfundraisingevents.entity.CollectionBox;
import com.task.collectionboxesforfundraisingevents.entity.CollectionBoxContent;
import com.task.collectionboxesforfundraisingevents.entity.FundraisingEvent;
import com.task.collectionboxesforfundraisingevents.entity.FundraisingEventAccount;
import com.task.collectionboxesforfundraisingevents.model.Currency;
import com.task.collectionboxesforfundraisingevents.repository.CollectionBoxContentRepository;
import com.task.collectionboxesforfundraisingevents.repository.CollectionBoxRepository;
import com.task.collectionboxesforfundraisingevents.repository.FundraisingEventAccountRepository;
import com.task.collectionboxesforfundraisingevents.repository.FundraisingEventRepository;
import com.task.collectionboxesforfundraisingevents.service.collectionBox.CollectionBoxService;
import com.task.collectionboxesforfundraisingevents.service.collectionBox.dto.CollectionBoxDto;
import com.task.collectionboxesforfundraisingevents.service.exchange.ExchangeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectionBoxServiceImpl implements CollectionBoxService {
    private final CollectionBoxRepository collectionBoxRepository;
    private final FundraisingEventRepository fundraisingEventRepository;
    private final CollectionBoxContentRepository collectionBoxContentRepository;
    private final FundraisingEventAccountRepository fundraisingEventAccountRepository;
    private final ExchangeService exchangeService;

    @Override
    @Transactional
    public CollectionBoxDto createCollectionBox() {
        return new CollectionBoxDto(collectionBoxRepository.save(new CollectionBox()));
    }

    @Override
    public List<CollectionBoxDto> getAllCollectionBoxes() {
        return collectionBoxRepository.findAll().stream().map(CollectionBoxDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeCollectionBox(Integer id) {
        if (!collectionBoxRepository.existsById(id))
            throw new EntityNotFoundException("Collection Box with id " + id + " not found");

        collectionBoxRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void assignCollectionBox(Integer collectionBoxId, Integer fundraisingEventId) {
        CollectionBox box = collectionBoxRepository.findById(collectionBoxId)
                .orElseThrow(() -> new EntityNotFoundException("Collection box not found"));

        FundraisingEvent event = fundraisingEventRepository.findById(fundraisingEventId)
                .orElseThrow(() -> new EntityNotFoundException("Fundraising event not found"));

        if (!box.isEmpty())
            throw new IllegalStateException("Collection box must be empty before assigning");

        box.setFundraisingEvent(event);
        box.setAssigned(true);
        collectionBoxRepository.save(box);
    }

    @Override
    @Transactional
    public void addMoneyToCollectionBox(Integer collectionBoxId, String currency, BigDecimal amount) {
        CollectionBox box = collectionBoxRepository.findById(collectionBoxId)
                .orElseThrow(() -> new EntityNotFoundException("Collection box not found"));

        if (!Currency.isValid(currency))
            throw new IllegalArgumentException(
                    "Invalid currency: " + currency + ". Currency must be one of: " + Arrays.toString(Currency.values())
            );

        if(Objects.isNull(amount) || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Amount must be greater than zero");

        Optional<CollectionBoxContent> optionalContent = collectionBoxContentRepository.findByCollectionBoxIdAndCurrency(collectionBoxId, currency);

        CollectionBoxContent content = optionalContent.orElseGet(() -> {
            CollectionBoxContent newContent = new CollectionBoxContent();
            newContent.setCollectionBox(box);
            newContent.setCurrency(currency);
            box.setIsEmpty(false);
            collectionBoxRepository.save(box);
            return newContent;
        });

        content.setAmount(content.getAmount().add(amount));
        collectionBoxContentRepository.save(content);
    }

    @Override
    @Transactional
    public void transferMoneyToFundraisingEvent(Integer collectionBoxId) {
        CollectionBox box = collectionBoxRepository.findById(collectionBoxId)
                .orElseThrow(() -> new EntityNotFoundException("Collection box not found"));

        if (box.isEmpty())
            throw new IllegalStateException("Collection box is empty");

        if (box.getFundraisingEvent() == null)
            throw new IllegalStateException("Collection box is not assigned to any fundraising event");

        FundraisingEvent event = box.getFundraisingEvent();
        FundraisingEventAccount account = event.getAccount();

        List<CollectionBoxContent> contentList = collectionBoxContentRepository
                .findByCollectionBoxId(collectionBoxId)
                .orElseThrow(() -> new EntityNotFoundException("No contents found for collection box"));

        account.setBalance(account.getBalance().add(calculateCollectionBoxTotal(contentList, event.getCurrency())));
        collectionBoxContentRepository.deleteAll(contentList);
        box.setIsEmpty(true);
        collectionBoxRepository.save(box);
        fundraisingEventAccountRepository.save(account);
    }

    private BigDecimal calculateCollectionBoxTotal(List<CollectionBoxContent> contentList, String eventCurrency) {
        Set<String> currenciesToConvert = new HashSet<>();

        for (CollectionBoxContent content : contentList)
            if (!content.getCurrency().equalsIgnoreCase(eventCurrency))
                currenciesToConvert.add(content.getCurrency());

        Map<String, BigDecimal> exchangeRates;
        if (currenciesToConvert.isEmpty())
            exchangeRates = Map.of();
        else
            exchangeRates = exchangeService.getExchangeRates(eventCurrency, currenciesToConvert);

        BigDecimal total = BigDecimal.ZERO;
        for (CollectionBoxContent content : contentList) {
            String currency = content.getCurrency();
            BigDecimal amount = content.getAmount();

            if (currency.equalsIgnoreCase(eventCurrency))
                total = total.add(amount);
            else {
                String rateKey = eventCurrency.toUpperCase() + currency.toUpperCase();
                BigDecimal rate = exchangeRates.get(rateKey);

                if (Objects.isNull(rate))
                    throw new RuntimeException("Missing exchange rate for: " + rateKey);

                BigDecimal converted = amount.multiply(BigDecimal.ONE.divide(rate, 6, RoundingMode.HALF_UP));
                total = total.add(converted.setScale(2, RoundingMode.HALF_UP));
            }
        }

        return total;
    }
}
