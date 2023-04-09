package com.company.models.casino;

/**
 * Игровая ставка.
 */
public final class Bet {
    private Long _id;
    private Long _userId;
    private Long _outcomeId;
    private final Double _price;
    private BetStatus _status = BetStatus.Unknown;

    /**
     * Создает новый объек типа {@link Bet}.
     * @param betId Идентификатор ставки.
     * @param price Цена пари.
     */
    public Bet(
        Long id,
        Long userId,
        Long outcomeId,
        Double price) throws IllegalArgumentException {

        _id = id;
        _userId = userId;
        _outcomeId = outcomeId;

        if (price <= 1.0)
            throw new IllegalArgumentException("Price can't be equal or less then 1.0");

        _price = price;
    }

    /**
     * Возвращает идентификатор ставки.
     */
    public Long getId() {
        return _id;
    }

    /**
     * Возвращает идентификатор пользователя, совершившего ставку.
     */
    public Long getUserId() {
        return _userId;
    }

    /**
     * Возвращает идентификатор исхода, на который сделана ставка.
     */
    public Long getOutcomeId() {
        return _outcomeId;
    }

    /**
     * Возвращает размер ставки.
     */
    public Double getPrice() {
        return _price;
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
