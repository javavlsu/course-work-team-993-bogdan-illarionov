package com.company.logic;

import com.company.abstractions.IGamePlayer;
import com.company.abstractions.IGamePlayerFactory;
import com.company.storage.models.StorageLot;
import com.company.storage.models.StorageUser;

public class GamePlayerFactory implements IGamePlayerFactory {
    private final DefaultGamePlayer defaultGamePlayer = new DefaultGamePlayer();

    @Override
    public IGamePlayer createGamePlayer(StorageUser user, StorageLot lot) { //todo
        var chance = user.getIncreasedChance();

        //if (chance)
        return null;
    }
}
