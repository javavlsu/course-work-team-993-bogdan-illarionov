package com.company.abstractions;

import com.company.models.casino.GameOutcome;

import java.util.List;

/**
 * Описывает проигрывателя игры.
 */
public interface IGamePlayer {
    /**
     * Проигрывает игровые исходы.
     * @param gameOutcomes Коллекция возможных игровых исходов.
     * @return Игровой исход типа {@link GameOutcome} являющийся результатом игровой сессии.
     */
    public GameOutcome playGame(List<GameOutcome> gameOutcomes);
}
