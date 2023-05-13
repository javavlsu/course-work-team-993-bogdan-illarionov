package com.company.abstractions;

import java.math.BigDecimal;

public interface IReadOnlyPlayingResult {
    public boolean isWin();
    public BigDecimal getWinPrice();
    public long getBetId();
    public String getGameOutcomeView();
}