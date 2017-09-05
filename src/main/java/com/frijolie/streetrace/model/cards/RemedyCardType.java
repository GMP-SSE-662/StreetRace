package com.frijolie.streetrace.model.cards;

public enum RemedyCardType implements CardType {

    REPAIR(0,"/images/repair.png"),
    GASOLINE(0,"/images/gasoline.png"),
    SPARE_TIRE(0,"/images/spareTire.png"),
    ROLL(0,"/images/roll.png");

    private final int value;
    private final String path;

    private RemedyCardType(int value, String path) {
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
