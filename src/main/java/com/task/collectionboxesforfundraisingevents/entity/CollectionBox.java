package com.task.collectionboxesforfundraisingevents.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "fundraisingEvent")
@Builder
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

    public boolean isEmpty() {
        return isEmpty;
    }
}
