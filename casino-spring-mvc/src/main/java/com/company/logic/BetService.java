package com.company.logic;

import com.company.abstractions.IBetRepository;
import com.company.abstractions.IBetService;
import com.company.abstractions.IRepository;
import com.company.abstractions.IUserRepository;
import com.company.storage.jpa.IUserJpaRepository;
import com.company.storage.models.StorageBet;
import com.company.storage.models.StorageLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BetService implements IBetService {
    @Autowired
    public IUserRepository userRepository;

    @Autowired
    public IBetRepository betRepository;

    @Transactional
    @Override
    public Set<StorageBet> GetBetsByLoginUser(String login) {
        var user = userRepository.getByLogin(login);

        if (user.isEmpty()) {
            return Collections.emptySet();
        }

        return betRepository.GetBetsByUserId(user.get().getId());
    }
}