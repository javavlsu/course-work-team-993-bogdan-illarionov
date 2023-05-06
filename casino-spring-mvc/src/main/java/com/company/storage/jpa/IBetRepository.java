package com.company.storage.jpa;

import com.company.storage.models.StorageBet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface IBetRepository extends JpaRepository<StorageBet, Long> {
    @Transactional
    @Query("select b from StorageBet b " +
            "INNER JOIN b.user u " +
            "INNER JOIN FETCH b.outcome o " +
            "INNER JOIN FETCH o.lot l " +
            "WHERE u.id = ?1 " +
            "ORDER BY b.id ")
    Set<StorageBet> findAllByUserId(Long userId);
}
