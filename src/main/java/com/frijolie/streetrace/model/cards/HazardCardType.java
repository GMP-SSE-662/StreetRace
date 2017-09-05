package com.frijolie.streetrace.model.cards;

public enum HazardCardType implements CardType {

    ACCIDENT(0,"ACCIDENT"),
    OUT_OF_GAS(0,"OUT_OF_GAS"),
    FLAT_TIRE(0,"FLAT_TIRE"),
    STOP(0,"STOP");

    private final int value;
    private final String name;

    private HazardCardType(int value, String name) {
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