package com.company.abstractions.storage;

import com.company.storage.models.StorageBet;

import java.util.List;
import java.util.Set;

public interface IBetRepository extends IRepository<StorageBet, Long>, IPageRepository<StorageBet> {
    Set<StorageBet> getBetsByUserId(Long userId);

    List<StorageBet> getPartBetsOfUser(Long userId, Integer quantity, Integer skipQuantity);

    List<StorageBet> getPartOgBets(int quantity, int skipQuantity);

    int getCountOfUser(Long userId);
}
