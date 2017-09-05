package com.frijolie.streetrace.model;

public enum RemedyCardType implements CardType {

    REPAIR(0),
    GASOLINE(0),
    SPARE_TIRE(0),
    ROLL(0);

    private final int value;

    private RemedyCardType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
