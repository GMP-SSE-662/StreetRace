package com.frijolie.streetrace.model;

public enum HazardCardType implements CardType {

    ACCIDENT(0,"/images/accident.png"),
    OUT_OF_GAS(0,"/images/outofgas.png"),
    FLAT_TIRE(0,"/images/flattire.png"),
    STOP(0,"/images/stop/png");

    private final int value;
    private final String path;

    private HazardCardType(int value, String path) {
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