package com.company.models.casino;

import java.math.BigDecimal;

/**
 * Игровая ставка.
 */
public final class Bet {
    private String _userLogin;
    private Long _outcomeId;
    private final BigDecimal _price;
    private BetStatus _status = BetStatus.Unknown;

    /**
     * Создает новый объек типа {@link Bet}.
     * @param price Цена пари.
     */
    public Bet(
        String userLogin,
        Long outcomeId,
        BigDecimal price) throws IllegalArgumentException {

        _userLogin = userLogin;
        _outcomeId = outcomeId;

        if (price.compareTo(BigDecimal.ONE) == -1)
            throw new IllegalArgumentException("Price can't be equal or less then 1.0");

        _price = price;
    }

    /**
     * Возвращает идентификатор пользователя, совершившего ставку.
     */
    public String getUserLogin() {
        return _userLogin;
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
    public BigDecimal getPrice() {
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
