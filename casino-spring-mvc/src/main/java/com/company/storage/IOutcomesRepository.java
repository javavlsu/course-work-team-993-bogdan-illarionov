package com.company.storage;

import com.company.storage.models.StorageOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOutcomesRepository extends JpaRepository<StorageOutcome, Long> {
}
