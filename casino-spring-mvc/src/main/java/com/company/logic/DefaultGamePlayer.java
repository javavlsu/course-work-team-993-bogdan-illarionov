package com.company.logic;

import com.company.abstractions.IGamePlayer;
import com.company.models.casino.GameOutcome;

import java.util.List;
import java.util.Random;

/**
 * Проигрыватель по умолчанию. Возвращает результат игры.
 */
public class DefaultGamePlayer implements IGamePlayer {
    private final Random _random = new Random();

    @Override
    public GameOutcome playGame(List<GameOutcome> gameOutcomes) {
        return gameOutcomes.get(_random.nextInt(gameOutcomes.size()));
    }
}
