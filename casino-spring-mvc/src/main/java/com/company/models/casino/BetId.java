package com.company.models.casino;

public class BetId {
    private Long _userId;
    private Long _outcomeId;

    public BetId(Long userId, Long outcomeId) {
        _userId = userId;
        _outcomeId = outcomeId;
    }

    public Long getUserId() {
        return _userId;
    }

    public Long getOutcomeId() {
        return _outcomeId;
    }
}
