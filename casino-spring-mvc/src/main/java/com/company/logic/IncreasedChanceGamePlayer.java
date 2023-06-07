package com.company.logic;

import com.company.abstractions.IGamePlayer;
import com.company.storage.models.StorageGameOutcome;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IncreasedChanceGamePlayer implements IGamePlayer {

    public static final int INCREASED_CHAHCE = 50;

    private final double ABSORPTION_COEFFICIENT = 1.6;
    private final int REDUCE_PROCENT_LIMIT = 10;
    private final int REDUCE = 10;
    private final int ADDITIONAL_REDUCE = 100;
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
    public StorageGameOutcome playGame(List<StorageGameOutcome> gameOutcomes) {
        var playedOutcomes = configureGameOutcomes(gameOutcomes);

        return wrappedGamePlayer.playGame(playedOutcomes);
    }

    private int calculateChance(int positive, int all){
        var probability = (double)positive / (double)all;

        return (int)(probability * 100);
    }
    private double calculateAdditionalChance(int chance){
        if (chance < REDUCE_PROCENT_LIMIT){
            chance *= REDUCE_PROCENT_LIMIT;
        }

        var reduced = chance / REDUCE;
        var additionalReduced = chance / ADDITIONAL_REDUCE;

        return increasedChance/(ABSORPTION_COEFFICIENT + reduced + additionalReduced);
    }

    private int calculateOutcomesCount(int count, int additionalChance, int chance){
        var newChance = chance + additionalChance;

        var reduceCoef = (newChance / chance);

        return count / reduceCoef;
    }

    private List<StorageGameOutcome> configureGameOutcomes(List<StorageGameOutcome> gameOutcomes){
        var chance = calculateChance(winningGameOutcomes.size(), gameOutcomes.size());
        var additionalChance = calculateAdditionalChance(chance);
        var outcomesCount = calculateOutcomesCount(gameOutcomes.size(), (int)additionalChance, chance);

        if (outcomesCount <= winningGameOutcomes.size())
            return winningGameOutcomes;

        var otherOutcomes = gameOutcomes.stream()
                .filter(x -> !winningGameOutcomes.contains(x))
                .collect(Collectors.toSet());

        var resultOutcomes = new ArrayList<StorageGameOutcome>();
        resultOutcomes.addAll(winningGameOutcomes);

        var currentCount = winningGameOutcomes.size();
        var iterator = otherOutcomes.iterator();
        while (currentCount < outcomesCount){
            resultOutcomes.add(iterator.next());
            currentCount++;
        }

        return resultOutcomes;
    }
}
