package com.company.storage.jpa;


import com.company.storage.models.StorageLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Хранилище игр.
 */
@Repository
public interface ILotRepository extends JpaRepository<StorageLot, Long> {

}
