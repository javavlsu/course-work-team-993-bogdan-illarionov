package com.company.abstractions;

import com.company.models.casino.GameOutcome;
import com.company.storage.models.StorageGameOutcome;

import java.util.List;

/**
 * Описывает проигрывателя игры.
 */
public interface IGamePlayer {
    /**
     * Проигрывает игровые исходы.
     * @param gameOutcomes Коллекция возможных игровых исходов.
     * @return Игровой исход типа {@link StorageGameOutcome} являющийся результатом игровой сессии.
     */
    public StorageGameOutcome playGame(List<StorageGameOutcome> gameOutcomes);
}
