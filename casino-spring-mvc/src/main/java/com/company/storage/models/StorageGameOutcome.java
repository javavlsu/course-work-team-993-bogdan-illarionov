package com.company.storage.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "game_outcomes")
public class StorageGameOutcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "view")
    private String view;

    @ManyToOne
    @JoinColumn(name="lot_id")
    private StorageLot lot;

    @ManyToMany(mappedBy = "relatedGameOutcomes")
    private Set<StorageOutcome> relatedOutcomes;

    public StorageGameOutcome(){

    }

    public StorageGameOutcome(Long id, String view, StorageLot lot) {
        this.id = id;
        this.view = view;
        this.lot = lot;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getView() {
        return view;
    }
    public void setView(String view) {
        this.view = view;
    }

    public StorageLot getLot() {
        return lot;
    }
    public void setLot(StorageLot lot) {
        this.lot = lot;
    }

    public Set<StorageOutcome> getRelatedOutcomes() {
        return relatedOutcomes;
    }

    public void setRelatedOutcomes(Set<StorageOutcome> relatedOutcomes) {
        this.relatedOutcomes = relatedOutcomes;
    }
}
