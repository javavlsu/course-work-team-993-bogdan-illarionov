package com.company.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LotRepositoryService {

    @Autowired
    public ILotRepository lotRepository;
}
