package com.company.storage.models;


import jakarta.persistence.*;

@Entity
@Table(name = "game_outcomes")
public class GameOutcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "view")
    private String view;
    @ManyToOne
    @JoinColumn(name="lot_id")
    private StorageLot lot;

    public StorageLot getLot() {
        return lot;
    }

    public GameOutcome(Long id, String view, StorageLot lot) {
        this.id = id;
        this.view = view;
        this.lot = lot;
    }

    public void setLot(StorageLot lot) {
        this.lot = lot;
    }

    public GameOutcome(){

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
}
