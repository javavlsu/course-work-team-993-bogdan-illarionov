package com.company;

import com.company.models.Lot;
import com.company.models.PlayingResult;

/**
 * Проигрыватель игровых лотов.
 */
public interface ILotsPlayer {

    /**
     * Запускает игру.
     * @param playingLot Лот, который будет воспроизводиться.
     * @return Результат игры типа {@link PlayingResult}
     */
    public PlayingResult PlayLot(Lot playingLot);
}
