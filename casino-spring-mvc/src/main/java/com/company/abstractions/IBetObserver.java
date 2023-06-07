package com.company.abstractions;

import com.company.models.casino.Bet;

public interface IBetObserver {
    public void update(Bet bet);
}
