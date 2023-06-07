package com.company.models;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BonusResult {
    private BigDecimal bonusKoef;

    private Set<Long> lotsList;

    public BonusResult() {
        bonusKoef = BigDecimal.ONE;
        lotsList = new HashSet<>();
    }

    public boolean haveResult() {
        return (bonusKoef.compareTo(BigDecimal.ONE) != 0) || (lotsList.size() != 0);
    }

    public BigDecimal getBonusKoef() {
        return bonusKoef;
    }

    public void setBonusKoef(BigDecimal bonusKoef) {
        this.bonusKoef = bonusKoef;
    }

    public void setLotsList(Set<Long> lotsList) {
        this.lotsList = lotsList;
    }

    public Set<Long> getLotsList() {
        return lotsList;
    }
}
