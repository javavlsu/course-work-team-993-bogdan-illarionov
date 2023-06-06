package com.company.storage.jpa.bonus;

import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageUserBonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface IUserBonusJpaRepository extends JpaRepository<StorageUserBonus, Long> {

    @Query("SELECT u FROM StorageUserBonus u WHERE u.userId = :givenUserId")
    Set<StorageUserBonus> getUsersBonuses(@Param("givenUserId") Long givenUserId);

    @Modifying
    @Query("update StorageUserBonusConfig u " +
            "set u.value = :givenValue " +
            "where u.mapId = :givenMapId and u.name = :givenName")
    void updateConfig(@Param("givenMapId") Long givenMapId,
                      @Param("givenName") String givenName,
                      @Param("givenValue") String givenValue);

}
