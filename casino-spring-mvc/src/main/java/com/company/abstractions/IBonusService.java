package com.company.abstractions;

import com.company.models.account.User;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageUserBonus;

import java.util.List;
import java.util.Optional;

public interface IBonusService {

    void syncBonuses(User user);

    List<StorageUserBonus> getBonusesForUser(User user);

    void addBonusToUser(StorageBonus bonus, User user);

    void changeBonusOfUser(StorageUserBonus userBonus);
}
