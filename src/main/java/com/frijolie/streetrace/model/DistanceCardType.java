package com.frijolie.streetrace.model;

public enum DistanceCardType implements CardType {
    MILES_25(25,"/images/25.png"),
    MILES_50(50,"/images/50.png"),
    MILES_75(75,"/images/75.png"),
    MILES_100(100,"/images/100.png"),
    MILES_200(200,"/images/200.png");

    private final int value;
    private final String path;

    private DistanceCardType(int value, String path) {
        this.value = value;
        this.path = path;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getPath() {
        return path;
    }

}