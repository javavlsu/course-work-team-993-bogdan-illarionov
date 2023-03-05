package com.company.storage;


import com.company.models.Lot;
import com.company.models.Outcome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Хранилище игр.
 */
public class LotRepository implements IRepository<Lot> {

    // todo: ORM
    private ArrayList<Lot> _lots = new ArrayList<>(
            Arrays.asList(
                    new Lot(1,"lot 1 name", "some desc", Arrays.asList(
                            new Outcome(1,"1", 1.1),
                            new Outcome(2,"2", 1.1),
                            new Outcome(3,"3", 1.1)
                    ))
            )
    );

    public Iterable<Lot> getAll() {
        return _lots;
    }

    public Lot getById(long id) throws IllegalArgumentException {
        for (var lot : _lots) {
            if (lot.getId() == id)
                return lot;
        }

        throw new IllegalArgumentException();
    }

    public void add(Lot lot) {

    }
}
