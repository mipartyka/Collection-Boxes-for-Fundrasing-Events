package com.task.collectionboxesforfundraisingevents.service.collectionBox.dto;

import com.task.collectionboxesforfundraisingevents.entity.CollectionBox;
import lombok.Getter;

import java.util.Objects;

@Getter
public class CollectionBoxDto {
    private Integer id;
    private boolean isEmpty;
    private boolean assigned;
//    private String fundraisingEventName;

    public CollectionBoxDto(CollectionBox collectionBox) {
        this.id = collectionBox.getId();
        this.isEmpty = collectionBox.getIsEmpty();
        this.assigned = collectionBox.getAssigned();
//        this.fundraisingEventName = Objects.isNull(collectionBox.getFundraisingEvent()) ? "" : collectionBox.getFundraisingEvent().getName();
    }
}
