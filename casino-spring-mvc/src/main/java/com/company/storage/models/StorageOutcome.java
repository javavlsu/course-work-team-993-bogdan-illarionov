package com.company.storage.models;

import com.company.models.casino.Outcome;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "outcomes")
public class StorageOutcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "koef")
    private BigDecimal koef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lot_id")
    private StorageLot lot;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "outcomes_game_outcomes",
            joinColumns = @JoinColumn(name = "outcome_id"),
            inverseJoinColumns = @JoinColumn(name = "game_outcome_id"))
    private Set<StorageGameOutcome> relatedGameOutcomes;

    public StorageOutcome(){

    }

    public StorageOutcome(
            Long id,
            String value,
            BigDecimal koef,
            StorageLot lot,
            Set<StorageGameOutcome> relatedGameOutcomes) {
        this.id = id;
        this.value = value;
        this.koef = koef;
        this.lot = lot;
        this.relatedGameOutcomes = relatedGameOutcomes;
    }

    public static Outcome fromStorageModel(StorageOutcome storageOutcome){
        return new Outcome(
                storageOutcome.getId(),
                storageOutcome.getValue(),
                storageOutcome.getKoef(),
                storageOutcome.getRelatedGameOutcomes().stream()
                        .map(StorageGameOutcome::fromStorageModel)
                        .collect(Collectors.toSet()));
    }

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

    public void setKoef(BigDecimal koef) {
        this.koef = koef;
    }
    public BigDecimal getKoef() {
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
