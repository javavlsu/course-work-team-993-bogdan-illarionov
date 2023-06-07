package com.company.storage.models;

import com.company.models.casino.Lot;
import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "lots")
public class StorageLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link")
    private String link;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "lot", fetch = FetchType.EAGER)
    private Set<StorageGameOutcome> gameOutcomes;

    @OneToMany(mappedBy = "lot", fetch = FetchType.EAGER)
    private Set<StorageOutcome> outcomes;

    public StorageLot(){

    }

    public StorageLot(
            Long id,
            String name,
            String description,
            Set<StorageGameOutcome> gameOutcomes,
            Set<StorageOutcome> outcomes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gameOutcomes = gameOutcomes;
        this.outcomes = outcomes;
    }

    public Lot fromStorageModel(){
        return new Lot (
                this.getId(),
                this.getName(),
                this.getDescription(),
                this.getOutcomes().stream()
                        .map(StorageOutcome::fromStorageModel)
                        .collect(Collectors.toSet()),
                this.getGameOutcomes().stream()
                        .map(StorageGameOutcome::fromStorageModel)
                        .collect(Collectors.toSet()));
    }


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

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
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
