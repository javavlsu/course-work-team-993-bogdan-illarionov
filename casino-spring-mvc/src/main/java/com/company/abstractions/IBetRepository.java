package com.company.abstractions;

import com.company.storage.models.StorageBet;

import java.util.Set;

public interface IBetRepository extends IRepository<StorageBet, Long>{
    public Set<StorageBet> GetBetsByUserId(Long userId);
}
