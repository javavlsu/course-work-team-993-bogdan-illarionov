package com.company.storage.jpa;


import com.company.storage.models.StorageLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Хранилище игр.
 */
@Repository
public interface ILotJpaRepository extends JpaRepository<StorageLot, Long> {

}
