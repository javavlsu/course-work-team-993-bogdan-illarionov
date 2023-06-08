package com.company.storage.jpa;

import com.company.storage.models.StorageOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOutcomesJpaRepository extends JpaRepository<StorageOutcome, Long> {
}
