package com.company.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;


public class LotRepositoryService {

    public ILotRepository lotRepository;
}
