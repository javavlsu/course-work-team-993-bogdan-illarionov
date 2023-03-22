package com.company.storage;

import com.company.storage.models.GameOutcome;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameOutcomesRepo extends CrudRepository<GameOutcome, Long> {
}
