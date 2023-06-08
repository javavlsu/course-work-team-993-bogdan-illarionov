package com.company.abstractions;

import com.company.models.casino.Bet;
import com.company.models.casino.PlayingResult;

/**
 * Проигрыватель игровых лотов.
 */
public interface IBetApplicator {

    /**
     * Запускает игру.
     * @return Результат игры типа {@link IReadOnlyPlayingResult}
     */
    public IReadOnlyPlayingResult applyBet(Bet bet);
}
