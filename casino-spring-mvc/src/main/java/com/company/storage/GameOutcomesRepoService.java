package com.company.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@Service
public class GameOutcomesRepoService {
    @Autowired
    public GameOutcomesRepo gameOutcomesRepo;
}
