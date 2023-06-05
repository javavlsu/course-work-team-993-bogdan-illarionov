package com.company.abstractions;

import com.company.models.account.User;
import com.company.storage.models.StorageUser;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageUserBonus;

import java.util.List;

public interface IBonusService {

    List<StorageBonus> getBonuses();

    void createBonus(StorageBonus bonus);

    void updateBonus(StorageBonus bonus);

    List<StorageBonus> getBonusesForUser(User user);

    List<StorageUserBonus> getDetailBonusesForUser(User user);

    void addBonusToUser(StorageBonus bonus, User user);

    void changeBonusOfUser(StorageUserBonus userBonus);
}
