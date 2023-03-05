package com.company.storage;


import com.company.models.Bet;
import com.company.models.Lot;
import com.company.models.Outcome;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Хранилище игр.
 */
public class BetRepository implements IRepository<Bet> {

    // todo: ORM
    private ArrayList<Bet> _bets = new ArrayList<>();
    public Iterable<Bet> getAll() {
        return _bets;
    }

    public Bet getById(long id) throws IllegalArgumentException {
        for (var lot : _bets) {
            if (lot.getUserId() == id)
                return lot;
        }

        throw new IllegalArgumentException();
    }

    public void add(Bet lot) {

    }
}
