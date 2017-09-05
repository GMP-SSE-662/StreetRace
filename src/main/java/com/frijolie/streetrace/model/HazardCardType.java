package com.frijolie.streetrace.model;

public enum HazardCardType implements CardType {
    
    ACCIDENT(0),
    OUT_OF_GAS(0),
    FLAT_TIRE(0),
    STOP(0);
    
    private final int value;
    
    private HazardCardType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
    
}