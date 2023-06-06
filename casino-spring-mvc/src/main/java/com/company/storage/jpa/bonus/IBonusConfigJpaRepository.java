package com.company.storage.jpa.bonus;

import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageBonusConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBonusConfigJpaRepository extends JpaRepository<StorageBonusConfig, Long> {
}
