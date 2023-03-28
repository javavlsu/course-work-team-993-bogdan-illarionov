package com.company.storage;

import com.company.storage.models.StorageGameOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameOutcomesRepository extends JpaRepository<StorageGameOutcome, Long> {
}
