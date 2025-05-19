package com.task.collectionboxesforfundraisingevents.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CollectionBoxContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "collection_box_id", nullable = false)
    private CollectionBox collectionBox;
    @Column(length = 3, nullable = false)
    private String currency;
    @Column(precision = 19, scale = 4, nullable = false)
    private BigDecimal amount;
}
