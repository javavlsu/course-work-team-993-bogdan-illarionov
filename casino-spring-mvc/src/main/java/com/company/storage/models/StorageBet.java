package com.company.storage.models;

import com.company.models.casino.BetStatus;
import jakarta.persistence.*;

@Entity
public class StorageBet {
    @EmbeddedId
    private StorageBetId betId;
    @Column(name = "sum")
    private Double sum;
    @Column(name = "status")
    private BetStatus betStatus;

    public StorageBet() {
    }

    public StorageBet(StorageBetId betId, Double sum, BetStatus betStatus) {
        this.betId = betId;
        this.sum = sum;
        this.betStatus = betStatus;
    }

    public StorageBetId getBetId() {
        return betId;
    }

    public void setBetId(StorageBetId betId) {
        this.betId = betId;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public BetStatus getBetStatus() {
        return betStatus;
    }

    public void setBetStatus(BetStatus betStatus) {
        this.betStatus = betStatus;
    }
}
