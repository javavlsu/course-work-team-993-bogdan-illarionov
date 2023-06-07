package com.company.storage.jpa;

import com.company.storage.models.StorageBet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface IBetJpaRepository extends JpaRepository<StorageBet, Long> {
    @Transactional
    @Query("select b from StorageBet b " +
            "INNER JOIN b.user u " +
            "INNER JOIN FETCH b.outcome o " +
            "INNER JOIN FETCH o.lot l " +
            "WHERE u.id = ?1 " +
            "ORDER BY b.id ")
    Set<StorageBet> findAllByUserId(Long userId);

    @Query("select b from StorageBet b " +
            "INNER JOIN b.user u " +
            "INNER JOIN FETCH b.outcome o " +
            "INNER JOIN FETCH o.lot l " +
            "WHERE u.id = :user " +
            "order by b.id " +
            "limit :quantity offset :skip")
    Set<StorageBet> getQuantityOfUser(
            @Param("user") Long userId,
            @Param("quantity") Integer quantity,
            @Param("skip") Integer skipQuantity);

    @Query("select b from StorageBet b " +
            "INNER JOIN b.user u " +
            "INNER JOIN FETCH b.outcome o " +
            "INNER JOIN FETCH o.lot l " +
            "order by b.id DESC " +
            "limit :quantity offset :skip")
    Set<StorageBet> getQuantity(
            @Param("quantity") Integer quantity,
            @Param("skip") Integer skipQuantity);

    @Query("select count(b) from StorageBet b " +
            "INNER JOIN b.user u " +
            "WHERE u.id = :user ")
    Integer getCountOfUser(@Param("user") Long userId);
}
