package com.company.service;

import com.company.abstractions.ILotsPlayer;
import com.company.models.Bet;
import com.company.models.Lot;
import com.company.models.PlayingResult;

import java.util.Arrays;
import java.util.Random;

/**
 * Проигрыватель игр. /Todo нужно создать цепочку декораторов над проигрывателем, которые будут давать бонусы разные
 */
public class LotsPlayer implements ILotsPlayer {
    private final Random _random = new Random();

    public PlayingResult playLot(Lot playingLot, Bet bet) {
        var outcomes = Arrays.asList(playingLot.getOutcomes());

        var winOutcome = outcomes.get(_random.nextInt(outcomes.size()));
        return null;
    }
}
