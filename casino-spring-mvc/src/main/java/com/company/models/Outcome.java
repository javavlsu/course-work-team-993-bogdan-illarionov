package com.company.models;

import java.util.Objects;

/**
 * Игровой исход.
 */
public final class Outcome {
    private final long _id;
    private final String _value;
    private final double _coefficient;

    /**
     * Создает новый объект типа {@link Outcome}.
     * @param id Идентификатор исхода.
     * @param value Значение исхода.
     * @param coefficient Коэффицент исхода.
     */
    public Outcome(long id, String value, double coefficient) throws IllegalArgumentException {
        _id = id;

        if (value.isBlank())
            throw new IllegalArgumentException("Value can't be empty");
        _value = value;

        if (coefficient <= 1.0)
            throw new IllegalArgumentException("Coefficient can't be equals or less then 1.0");
        _coefficient = coefficient;
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
    public String getValue(){
        return _value;
    }

    /**
     * Возвращает коэффициент игрового исхода.
     * @return Объект типа {@link double}.
     */
    public double getCoefficient(){
        return _coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outcome outcome = (Outcome) o;
        return _id == outcome._id &&
            Double.compare(outcome._coefficient, _coefficient) == 0 &&
            _value.equals(outcome._value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(_id, _value, _coefficient);
    }
}
