package com.company.storage.jpa;

import com.company.storage.models.StorageUser;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageUserBonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface IBonusJpaRepository extends JpaRepository<StorageBonus, Long> {

    @Query("SELECT u FROM StorageUserBonus u WHERE u.userId = :givenUserId")
    Set<StorageUserBonus> getUsersBonuses(@Param("givenUserId") Long givenUserId);
}
