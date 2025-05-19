package com.task.collectionboxesforfundraisingevents.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FundraisingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(length = 3, nullable = false)
    private String currency;
    @OneToOne(mappedBy = "fundraisingEvent", cascade = CascadeType.ALL)
    private FundraisingEventAccount account;
}
