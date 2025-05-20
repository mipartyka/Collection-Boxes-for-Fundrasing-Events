package com.task.collectionboxesforfundraisingevents.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "account")
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
