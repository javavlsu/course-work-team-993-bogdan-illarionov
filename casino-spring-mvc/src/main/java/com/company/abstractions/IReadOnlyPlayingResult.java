package com.company.abstractions;

public interface IReadOnlyPlayingResult {
    public boolean isWin();
    public double getWinPrice();
    public long getBetId();
    public String getGameOutcomeView();
}