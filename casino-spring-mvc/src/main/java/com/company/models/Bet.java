package com.company.models;

public final class Bet {
    private final long _userId;
    private final long _outcomeId;
    private final double _price;
    private BetStatus _status;

    public Bet(
        long userId,
        long outcomeId,
        double price){

        _userId = userId;
        _outcomeId = outcomeId;
        _status = BetStatus.Unknown;

        if (price <= 1.0)
            throw new IllegalArgumentException("Price can't be equal or less then 1.0");

        _price = price;
    }

    public long getUserId(){
        return _userId;
    }

    public long getOutcomeId(){
        return _outcomeId;
    }

    public BetStatus getStatus(){
        return _status;
    }
    public void setStatus(BetStatus status){
        _status = status;
    }
}
