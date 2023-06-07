package com.company.abstractions;

import com.company.models.BonusResult;
import com.company.models.account.User;
import com.company.models.enums.BonusTriggerAction;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageUserBonus;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

public interface IBonusService {

    void syncBonuses(User user);

    List<StorageUserBonus> getBonusesForUser(User user);

    void addBonusToUser(StorageBonus bonus, User user);

    void changeBonusOfUser(StorageUserBonus userBonus);

    Optional<BonusResult> triggerBonuses(User user, BonusTriggerAction action);

    void expireBonus(User user, BonusTriggerAction action);
}
