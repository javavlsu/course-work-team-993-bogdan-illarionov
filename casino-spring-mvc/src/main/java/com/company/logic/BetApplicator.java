package com.company.logic;

import com.company.abstractions.IBetApplicator;
import com.company.abstractions.IReadOnlyPlayingResult;
import com.company.models.casino.Bet;
import com.company.models.casino.PlayingResult;

public class BetApplicator implements IBetApplicator {
    @Override
    public IReadOnlyPlayingResult applyBet(Bet bet) {
        return null;
    }
}
