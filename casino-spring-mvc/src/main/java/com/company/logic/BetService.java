package com.company.logic;

import com.company.abstractions.storage.IBetRepository;
import com.company.abstractions.IBetService;
import com.company.abstractions.storage.IUserRepository;
import com.company.storage.models.StorageBet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

@Service
public class BetService implements IBetService {
    @Autowired
    public IUserRepository userRepository;

    @Autowired
    public IBetRepository betRepository;

    @Transactional
    @Override
    public Set<StorageBet> getBetsByLoginUser(String login) {
        var user = userRepository.getByLogin(login);

        if (user.isEmpty()) {
            return Collections.emptySet();
        }

        return betRepository.getBetsByUserId(user.get().getId());
    }
}