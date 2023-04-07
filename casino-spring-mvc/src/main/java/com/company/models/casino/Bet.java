package com.company.models.casino;

/**
 * Игровая ставка.
 */
public final class Bet {
    private final BetId _id;
    private final Double _price;
    private BetStatus _status = BetStatus.Unknown;

    /**
     * Создает новый объек типа {@link Bet}.
     * @param betId Идентификатор ставки.
     * @param price Цена пари.
     */
    public Bet(
        BetId betId,
        Double price) throws IllegalArgumentException {

        _id = betId;

        if (price <= 1.0)
            throw new IllegalArgumentException("Price can't be equal or less then 1.0");

        _price = price;
    }

    /**
     * Возвращает идентификатор ставки.
     */
    public BetId getId() {
        return _id;
    }

    /**
     * Возвращает статус ставки.
     */
    public BetStatus getStatus(){
        return _status;
    }

    /**
     * Устанавливает статус ставки.
     */
    public void setStatus(BetStatus status){
        _status = status;
    }
}
