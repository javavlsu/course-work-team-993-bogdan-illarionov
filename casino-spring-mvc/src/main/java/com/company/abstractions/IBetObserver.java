package com.company.abstractions;

import com.company.models.Bet;

public interface IBetObserver {
    public void Update(Bet bet);
}
