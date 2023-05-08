package com.company.logic;

import com.company.abstractions.IBetRepository;
import com.company.abstractions.IBetService;
import com.company.abstractions.IRepository;
import com.company.storage.jpa.IUserJpaRepository;
import com.company.storage.models.StorageBet;
import com.company.storage.models.StorageLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BetService implements IBetService {
    @Autowired
    public IUserJpaRepository userRepository;

    @Autowired
    public IRepository<StorageLot, Long> lotRepository;

    @Autowired
    public IBetRepository betRepository;

    @Transactional
    @Override
    public Set<StorageBet> GetBetsByLoginUser(String login) {
        var user = userRepository.findByUserLogin(login);

        return betRepository.GetBetsByUserId(user.getId());
    }
}