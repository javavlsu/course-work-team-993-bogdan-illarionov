package com.company.storage.jpa;

import com.company.storage.models.StorageBet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface IBetRepository extends JpaRepository<StorageBet, Long> {
}
