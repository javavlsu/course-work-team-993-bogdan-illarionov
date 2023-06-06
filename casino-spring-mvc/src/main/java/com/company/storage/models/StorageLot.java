package com.company.storage.models;

import com.company.models.casino.Lot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "lots")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
