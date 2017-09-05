package com.frijolie.streetrace.model.cards;

public enum SafetyCardType implements CardType {
    DRIVING_ACE(100,"DRIVING_ACE"),
    EXTRA_TANK(100,"EXTRA_TANK"),
    PUNCTURE_PROOF(100,"PUNCTURE_PROOF"),
    RIGHT_OF_WAY(100,"RIGHT_OF_WAY");

    private final int value;
    private final String name;

    private SafetyCardType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }

}
