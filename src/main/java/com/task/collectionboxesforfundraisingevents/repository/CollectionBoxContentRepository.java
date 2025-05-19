package com.task.collectionboxesforfundraisingevents.repository;

import com.task.collectionboxesforfundraisingevents.entity.CollectionBoxContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CollectionBoxContentRepository extends JpaRepository<CollectionBoxContent, Integer> {
    Optional<CollectionBoxContent> findByCollectionBoxIdAndCurrency(Integer collectionBoxId, String currency);


}
