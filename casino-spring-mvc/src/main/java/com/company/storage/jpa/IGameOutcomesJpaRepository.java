package com.company.storage.jpa;

import com.company.storage.models.StorageGameOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameOutcomesJpaRepository extends JpaRepository<StorageGameOutcome, Long> {
}
