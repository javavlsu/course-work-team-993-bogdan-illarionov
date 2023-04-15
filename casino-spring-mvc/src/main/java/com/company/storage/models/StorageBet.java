package com.company.storage.models;

import com.company.models.casino.BetStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "bets")
public class StorageBet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private StorageUser user;
    @ManyToOne
    @JoinColumn(name="outcome_id")
    private StorageOutcome outcome;
    @Column(name = "sum")
    private Double sum;
    @Column(name = "status")
    private BetStatus betStatus;

    public StorageBet() {
    }
    public StorageBet(StorageUser user, StorageOutcome outcome, Double sum, BetStatus betStatus) {
        this.user = user;
        this.outcome = outcome;
        this.sum = sum;
        this.betStatus = betStatus;
    }
    public StorageBet(Long id, StorageUser user, StorageOutcome outcome, Double sum, BetStatus betStatus) {
        this(user, outcome, sum, betStatus);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StorageUser getUser() {
        return user;
    }

    public void setUser(StorageUser user) {
        this.user = user;
    }

    public StorageOutcome getOutcome() {
        return outcome;
    }

    public void setOutcome(StorageOutcome outcome) {
        this.outcome = outcome;
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
