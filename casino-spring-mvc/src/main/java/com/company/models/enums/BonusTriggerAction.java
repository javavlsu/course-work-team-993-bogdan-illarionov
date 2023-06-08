package com.company.models.enums;

public enum BonusTriggerAction {
    BonusAdd((short)1),
    LotWin((short)2),
    LotPlay((short)3);

    private final short id;
    BonusTriggerAction(short id) { this.id = id; }
    public short getValue() { return id; }
}
