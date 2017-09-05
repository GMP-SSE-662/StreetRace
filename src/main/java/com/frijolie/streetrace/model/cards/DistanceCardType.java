package com.frijolie.streetrace.model.cards;

public enum DistanceCardType implements CardType {
    MILES_25(25,"MILES_25"),
    MILES_50(50,"MILES_50"),
    MILES_75(75,"MILES_75"),
    MILES_100(100,"MILES_100"),
    MILES_200(200,"MILES_200");

    private final int value;
    private final String name;

    private DistanceCardType(int value, String name) {
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