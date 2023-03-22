package com.company.storage.models;

import jakarta.persistence.*;

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


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Set<GameOutcome> getGameOutcomes() {
        return gameOutcomes;
    }
    public void setGameOutcomes(Set<GameOutcome> gameOutcomes) {
        this.gameOutcomes = gameOutcomes;
    }
}
