package com.task.collectionboxesforfundraisingevents.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "collectionBox")
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
    private BigDecimal amount = BigDecimal.ZERO;
}
