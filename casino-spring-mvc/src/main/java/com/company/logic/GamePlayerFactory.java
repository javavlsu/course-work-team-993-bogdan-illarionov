package com.company.logic;

import com.company.abstractions.IGamePlayer;
import com.company.abstractions.IGamePlayerFactory;
import com.company.models.account.User;
import com.company.storage.models.StorageOutcome;
import com.company.storage.models.StorageUser;
import org.springframework.stereotype.Service;

@Service
public class GamePlayerFactory implements IGamePlayerFactory {
    private final DefaultGamePlayer defaultGamePlayer = new DefaultGamePlayer();
    private final Integer DEFAULT_CHANCE = 0;

    @Override
    public IGamePlayer createGamePlayer(
            User user,
            StorageOutcome selectedOutcome) {
        var chance = user.getChance();

        if (!chance.equals(DEFAULT_CHANCE)){
            return new IncreasedChanceGamePlayer(
                    defaultGamePlayer,
                    chance,
                    selectedOutcome.getRelatedGameOutcomes()
                            .stream().toList());
        }

        return defaultGamePlayer;
    }
}
