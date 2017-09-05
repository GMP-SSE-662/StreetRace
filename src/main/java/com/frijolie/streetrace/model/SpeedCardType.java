package com.frijolie.streetrace.model;

public enum SpeedCardType implements CardType {

    SPEED_LIMIT(0),
    END_LIMIT(0);

    private final int value;

    private SpeedCardType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

}
