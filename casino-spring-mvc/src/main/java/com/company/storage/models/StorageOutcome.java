package com.company.storage.models;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "outcomes")
public class StorageOutcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "koef")
    private Double koef;

    @ManyToOne
    @JoinColumn(name="lot_id")
    private StorageLot lot;

    @ManyToMany
    @JoinTable(
            name = "outcomes_game_outcomes",
            joinColumns = @JoinColumn(name = "outcome_id"),
            inverseJoinColumns = @JoinColumn(name = "game_outcome_id"))
    private Set<StorageGameOutcome> relatedGameOutcomes;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public void setKoef(Double koef) {
        this.koef = koef;
    }
    public Double getKoef() {
        return koef;
    }

    public StorageLot getLot() {
        return lot;
    }
    public void setLot(StorageLot lot) {
        this.lot = lot;
    }

    public Set<StorageGameOutcome> getRelatedGameOutcomes() {
        return relatedGameOutcomes;
    }

    public void setRelatedGameOutcomes(Set<StorageGameOutcome> relatedGameOutcomes) {
        this.relatedGameOutcomes = relatedGameOutcomes;
    }
}
