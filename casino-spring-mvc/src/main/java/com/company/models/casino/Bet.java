package com.company.models.casino;

/**
 * Игровая ставка.
 */
public final class Bet {
    private final long _userId;
    private final long _outcomeId;
    private final double _price;
    private BetStatus _status = BetStatus.Unknown;

    /**
     * Создает новый объек типа {@link Bet}.
     * @param userId Идентификатор пользователя, сделавший ставку.
     * @param outcomeId Идентификатор исхода, на который сделана ставка.
     * @param price Цена пари.
     */
    public Bet(
        long userId,
        long outcomeId,
        double price) throws IllegalArgumentException {

        _userId = userId;
        _outcomeId = outcomeId;

        if (price <= 1.0)
            throw new IllegalArgumentException("Price can't be equal or less then 1.0");

        _price = price;
    }

    /**
     * Возвращает идентификатор пользователя, сделавшего ставку.
     */
    public long getUserId(){
        return _userId;
    }
    /**
     * Возвращает идентификатор исхода, на который была сделана ставка.
     */
    public long getOutcomeId(){
        return _outcomeId;
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
