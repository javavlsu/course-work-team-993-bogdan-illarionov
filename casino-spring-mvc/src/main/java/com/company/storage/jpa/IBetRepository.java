package com.company.storage.jpa;

import com.company.storage.models.StorageBet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBetRepository extends JpaRepository<StorageBet, Long> {
}
