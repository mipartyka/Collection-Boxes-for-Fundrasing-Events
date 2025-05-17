package com.task.collectionboxesforfundraisingevents.entity;

import jakarta.persistence.*;

@Entity
public class CollectionBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean isEmpty = true;
    private Boolean assigned = false;
    @ManyToOne
    @JoinColumn(name = "fundraising_event_id")
    private FundraisingEvent fundraisingEvent;
}
