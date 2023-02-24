package com.company.models;

/**
 * Игровой исход.
 */
public final class Outcome {
    private final long _id;
    private final String _value;
    private final double _coefficient;

    /**
     * Создает новый объект типа {@link Lot}.
     * @param id Идентификатор исхода.
     * @param value Значение исхода.
     * @param coefficient Коэффицент исхода.
     */
    public Outcome(long id, String value, double coefficient){
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
}
