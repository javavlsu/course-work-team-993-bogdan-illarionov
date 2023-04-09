package com.company.logic;

import com.company.abstractions.IGamePlayer;
import com.company.abstractions.IGamePlayerFactory;
import com.company.storage.models.StorageOutcome;
import com.company.storage.models.StorageUser;
import org.springframework.stereotype.Service;

@Service
public class GamePlayerFactory implements IGamePlayerFactory {
    private final DefaultGamePlayer defaultGamePlayer = new DefaultGamePlayer();

    @Override
    public IGamePlayer createGamePlayer(
            StorageUser user,
            StorageOutcome selectedOutcome) {
        var chance = user.getIncreasedChance();

        if (chance != 0){
            return new IncreasedChanceGamePlayer(
                    defaultGamePlayer,
                    chance,
                    selectedOutcome.getRelatedGameOutcomes()
                            .stream().toList());
        }

        return defaultGamePlayer;
    }
}
