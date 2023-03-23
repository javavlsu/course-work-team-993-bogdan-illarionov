package com.company.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GameOutcomesRepoService {
    @Autowired
    public GameOutcomesRepo gameOutcomesRepo;
}
