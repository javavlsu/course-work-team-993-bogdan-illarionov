package com.company.storage;

import com.company.storage.models.GameOutcome;
import org.springframework.data.repository.CrudRepository;

public interface GameOutcomesRepo extends CrudRepository<GameOutcome, Long> {
}
