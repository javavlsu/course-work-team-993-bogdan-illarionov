package com.company.models.enums;

public enum BonusExpireType {
    Count((short)1),
    Term((short)2),
    Unlimited((short)3);

    private final short id;

    BonusExpireType(short id) { this.id = id; }

    public short getValue() { return id; }
}
