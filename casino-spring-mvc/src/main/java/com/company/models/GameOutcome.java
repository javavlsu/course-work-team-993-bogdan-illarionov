package com.company.models;

import java.util.Objects;

/**
 * Исход игрового автомата.
 */
public final class GameOutcome {
    private final long _id;
    private final String _view;

    /**
     * Создает новый объект типа {@link GameOutcome}.
     * @param id Идентификатор исхода игрового автомата.
     * @param view Текстовый вид исхода.
     */
    public GameOutcome(long id, String view) throws  IllegalArgumentException{
        _id = id;

        if (view.isBlank())
            throw new IllegalArgumentException("Value can't be empty");
        _view = view;
    }

    /**
     * Возвращает идентификатор игрового лота.
     * @return Объект типа {@link long}.
     */
    public long getId(){
        return _id;
    }

    /**
     * Возвращает значение исхода.
     * @return Объект типа {@link String}.
     */
    public String getView(){
        return _view;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameOutcome that = (GameOutcome) o;
        return _id == that._id && _view.equals(that._view);
    }
    @Override
    public int hashCode() {
        return Objects.hash(_id, _view);
    }
}
