package com.frijolie.streetrace.model;

public enum SafetyCardType implements CardType {
    DRIVING_ACE(100),
    EXTRA_TANK(100),
    PUNCTURE_PROOF(100),
    RIGHT_OF_WAY(100);

    private final int value;

    private SafetyCardType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

}
