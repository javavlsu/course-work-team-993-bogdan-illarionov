package com.company.abstractions;

import com.company.storage.models.StorageLot;
import com.company.storage.models.StorageOutcome;
import com.company.storage.models.StorageUser;

public interface IGamePlayerFactory {
    public IGamePlayer createGamePlayer(
            StorageUser user,
            StorageOutcome selectedOutcome);
}
