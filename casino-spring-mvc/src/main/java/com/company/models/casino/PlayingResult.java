package com.company.models.casino;

import com.company.abstractions.IReadOnlyPlayingResult;

import java.math.BigDecimal;

public final class PlayingResult implements IReadOnlyPlayingResult {
    private boolean isWin;
    private BigDecimal winPrice;
    private long betId;

    private String gameOutcomeView;

    public PlayingResult(
            boolean isWin,
            BigDecimal winPrice,
            long betId,
            String gameOutcomeView) {
        this.isWin = isWin;
        this.winPrice = winPrice;
        this.betId = betId;
        this.gameOutcomeView = gameOutcomeView;
    }

    public PlayingResult() {
    }

    @Override
    public boolean isWin() {
        return isWin;
    }

    @Override
    public String getGameOutcomeView() {
        return gameOutcomeView;
    }

    @Override
    public BigDecimal getWinPrice() {
        return winPrice;
    }

    @Override
    public long getBetId() {
        return betId;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public void setWinPrice(BigDecimal winPrice) {
        this.winPrice = winPrice;
    }

    public void setBetId(long betId) {
        this.betId = betId;
    }
}
