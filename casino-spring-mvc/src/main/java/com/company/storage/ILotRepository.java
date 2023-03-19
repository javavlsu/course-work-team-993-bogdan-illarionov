package com.company.storage;


import com.company.storage.models.StorageLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Хранилище игр.
 */

public interface ILotRepository extends CrudRepository<StorageLot, Long> {

}
