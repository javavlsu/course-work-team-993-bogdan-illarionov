package com.company.storage.models;

import com.company.models.casino.GameOutcome;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "game_outcomes")
public class StorageGameOutcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "view")
    private String view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lot_id")
    private StorageLot lot;

    public StorageGameOutcome(){

    }

    public StorageGameOutcome(Long id, String view, StorageLot lot) {
        this.id = id;
        this.view = view;
        this.lot = lot;
    }

    public static GameOutcome fromStorageModel(StorageGameOutcome storageGameOutcome){
        return new GameOutcome(
                storageGameOutcome.getId(),
                storageGameOutcome.getView(),
                storageGameOutcome.getLot().getId());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageGameOutcome that = (StorageGameOutcome) o;
        return Objects.equals(id, that.id) && Objects.equals(view, that.view) && Objects.equals(lot, that.lot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, view, lot);
    }
}
