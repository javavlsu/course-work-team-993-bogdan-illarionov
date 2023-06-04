package com.company.abstractions;

import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageUserBonus;

import java.util.Set;

public interface IBonusRepository extends IRepository<StorageBonus, Long> {
    Set<StorageUserBonus> getUsersBonuses(Long userId);

    void updateUserBonusConfig(StorageUserBonus storageUserBonus);
}
