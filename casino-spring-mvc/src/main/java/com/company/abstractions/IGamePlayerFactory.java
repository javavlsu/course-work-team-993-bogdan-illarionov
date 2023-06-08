package com.company.abstractions;

import com.company.models.account.User;
import com.company.storage.models.StorageLot;
import com.company.storage.models.StorageOutcome;
import com.company.storage.models.StorageUser;

public interface IGamePlayerFactory {
    public IGamePlayer createGamePlayer(
            User user,
            StorageOutcome selectedOutcome);
}
