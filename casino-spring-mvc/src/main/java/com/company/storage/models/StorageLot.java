package com.company.storage.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lots")
public class StorageLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @CollectionTable(name = "game_outcomes", joinColumns = @JoinColumn(name = "lot_id"))
    private Set<GameOutcome> gameOutcomes;

}
