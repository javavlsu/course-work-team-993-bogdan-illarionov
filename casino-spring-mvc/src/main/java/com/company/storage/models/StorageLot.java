package com.company.storage.models;

import jakarta.persistence.*;

import java.util.Collection;
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
    private Set<StorageGameOutcome> gameOutcomes;

    @OneToMany(mappedBy = "lot", fetch = FetchType.EAGER)
    private Set<StorageOutcome> outcomes;


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

    public Set<StorageGameOutcome> getGameOutcomes() {
        return gameOutcomes;
    }
    public void setGameOutcomes(Set<StorageGameOutcome> storageGameOutcomes) {
        this.gameOutcomes = storageGameOutcomes;
    }

    public Set<StorageOutcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(Set<StorageOutcome> outcomes) {
        this.outcomes = outcomes;
    }
}
