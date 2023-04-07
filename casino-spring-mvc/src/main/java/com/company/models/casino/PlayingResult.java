package com.company.models.casino;

import com.company.abstractions.IReadOnlyPlayingResult;

public final class PlayingResult implements IReadOnlyPlayingResult {
    private boolean isWin;
    private double winPrice;
    private long betId;

    @Override
    public boolean isWin() {
        return isWin;
    }

    @Override
    public double getWinPrice() {
        return winPrice;
    }

    @Override
    public long getBetId() {
        return betId;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public void setWinPrice(double winPrice) {
        this.winPrice = winPrice;
    }

    public void setBetId(long betId) {
        this.betId = betId;
    }
}
