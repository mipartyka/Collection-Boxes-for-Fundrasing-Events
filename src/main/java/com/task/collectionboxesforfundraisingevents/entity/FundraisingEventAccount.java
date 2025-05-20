package com.task.collectionboxesforfundraisingevents.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "fundraisingEvent")
@Builder
@Entity
public class FundraisingEventAccount {
    @Id
    private Integer fundraisingEventId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "fundraising_event_id")
    private FundraisingEvent fundraisingEvent;
    @Column(precision = 19, scale = 4, nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;
}
