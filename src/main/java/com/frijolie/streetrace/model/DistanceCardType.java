package com.frijolie.streetrace.model;

public enum DistanceCardType implements CardType {
    MILES_25(25),
    MILES_50(50),
    MILES_75(75),
    MILES_100(100),
    MILES_200(200);

    private final int value;

    private DistanceCardType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

}
