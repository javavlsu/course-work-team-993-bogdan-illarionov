package com.company.storage.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "lot", fetch = FetchType.EAGER)
    private Collection<GameOutcome> gameOutcomes;


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

    public Collection<GameOutcome> getGameOutcomes() {
        return gameOutcomes;
    }
    public void setGameOutcomes(Collection<GameOutcome> gameOutcomes) {
        this.gameOutcomes = gameOutcomes;
    }
}
