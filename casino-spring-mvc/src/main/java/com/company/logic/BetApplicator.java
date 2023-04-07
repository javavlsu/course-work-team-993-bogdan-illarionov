package com.company.logic;

import com.company.abstractions.IBetApplicator;
import com.company.abstractions.IReadOnlyPlayingResult;
import com.company.abstractions.IRepository;
import com.company.models.casino.Bet;
import com.company.storage.models.StorageBet;
import com.company.storage.models.StorageBetId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetApplicator implements IBetApplicator {
    @Autowired
    private IRepository<StorageBet, StorageBetId> betRepository;

    @Override
    public IReadOnlyPlayingResult applyBet(Bet bet) { //todo
        return null;
    }
}
