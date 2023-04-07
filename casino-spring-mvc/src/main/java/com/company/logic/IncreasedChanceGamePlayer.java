package com.company.logic;

import com.company.abstractions.IGamePlayer;
import com.company.models.casino.GameOutcome;
import com.company.storage.models.StorageGameOutcome;

import java.util.List;

public class IncreasedChanceGamePlayer implements IGamePlayer {
    private final IGamePlayer wrappedGamePlayer;
    private final int increasedChance;
    private final List<StorageGameOutcome> winningGameOutcomes;

    public IncreasedChanceGamePlayer(
            IGamePlayer wrappedGamePlayer,
            int increasedChance,
            List<StorageGameOutcome> winningGameOutcomes) {
        if (wrappedGamePlayer == null)
            throw new IllegalArgumentException();
        if (winningGameOutcomes == null)
            throw new IllegalArgumentException();

        this.wrappedGamePlayer = wrappedGamePlayer;
        this.increasedChance = increasedChance;
        this.winningGameOutcomes = winningGameOutcomes;
    }

    @Override
    public GameOutcome playGame(List<GameOutcome> gameOutcomes) { //todo
        //var

        return null;
    }
}
