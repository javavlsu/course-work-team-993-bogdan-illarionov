package com.company.abstractions;

import com.company.storage.models.StorageBet;

import java.util.Set;

public interface IBetService {
    public Set<StorageBet> getBetsByLoginUser(String login);
}
