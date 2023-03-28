package com.company.models;

import java.util.List;
import java.util.Set;

/**
 * Игровой лот.
 */
public final class Lot {
    private final long _id;
    private final String _name;
    private final String _description;
    private final Set<Outcome> _outcomes;
    private final Set<GameOutcome> _gameOutcomes;

    /**
     * Создает новый объект типа {@link Lot}.
     * @param id Идентификатор лота.
     * @param name Имя лота.
     * @param description Описание лота.
     * @param outcomes Исходы лота.
     */
    public Lot(
        long id,
        String name,
        String description,
        Set<Outcome> outcomes,
        Set<GameOutcome> gameOutcomes) throws IllegalArgumentException {
        _id = id;

        if (name.isBlank())
            throw new IllegalArgumentException("Name can't be empty");
        _name = name;

        if (description.isBlank())
            throw new IllegalArgumentException("Description can't be empty");
        _description = description;

        if (outcomes == null || outcomes.isEmpty())
            throw new IllegalArgumentException("Outcomes can't be empty");
        _outcomes = outcomes;

        if (gameOutcomes == null || gameOutcomes.isEmpty())
            throw new IllegalArgumentException("Game outcomes can't be empty");
        _gameOutcomes = gameOutcomes;
    }

    /**
     * Возвращает идентификатор игрового лота.
     * @return Объект типа {@link long}.
     */
    public long getId(){
        return _id;
    }

    /**
     * Возвращает имя игрового лота.
     * @return Объект типа {@link String}.
     */
    public String getName(){
        return _name;
    }

    /**
     * Возвращает описание игрового лота.
     * @return Объект типа {@link String}.
     */
    public String getDescription(){
        return _description;
    }

    /**
     * Возвращает исходы игрового лота.
     * @return Объект типа {@link Iterable}.
     */
    public Iterable<Outcome> getOutcomes(){
        return _outcomes;
    }

    /**
     * Возвращает исходы игрового автомата.
     * @return Объект типа {@link Iterable}.
     */
    public Iterable<GameOutcome> getGameOutcomes(){
        return _gameOutcomes;
    }
}