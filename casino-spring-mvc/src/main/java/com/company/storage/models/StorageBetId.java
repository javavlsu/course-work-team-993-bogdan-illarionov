package com.company.storage.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class StorageBetId implements Serializable {
    @ManyToOne
    @JoinColumn(name="user_id")
    private StorageUser userId;
    @ManyToOne
    @JoinColumn(name="outcome_id")
    private StorageOutcome outcomeId;
}
