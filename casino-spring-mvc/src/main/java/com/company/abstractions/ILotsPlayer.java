package com.company.abstractions;

import com.company.models.casino.Bet;
import com.company.models.casino.Lot;
import com.company.models.casino.PlayingResult;

/**
 * Проигрыватель игровых лотов.
 */
public interface ILotsPlayer {

    /**
     * Запускает игру.
     * @param playingLot Лот, который будет воспроизводиться.
     * @return Результат игры типа {@link PlayingResult}
     */
    public PlayingResult playLot(Lot playingLot, Bet bet);
}
