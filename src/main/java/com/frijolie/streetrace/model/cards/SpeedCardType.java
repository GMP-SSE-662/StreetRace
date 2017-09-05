package com.frijolie.streetrace.model.cards;

public enum SpeedCardType implements CardType {

    SPEED_LIMIT(0,"SPEED_LIMIT"),
    END_LIMIT(0,"END_LIMIT");

    private final int value;
    private final String name;

    private SpeedCardType(int value, String name) {
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
