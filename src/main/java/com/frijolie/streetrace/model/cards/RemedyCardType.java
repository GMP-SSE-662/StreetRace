package com.frijolie.streetrace.model.cards;

public enum RemedyCardType implements CardType {

    REPAIR(0,"REPAIR"),
    GASOLINE(0,"GASOLINE"),
    SPARE_TIRE(0,"SPARE_TIRE"),
    ROLL(0,"ROLL");

    private final int value;
    private final String name;

    private RemedyCardType(int value, String name) {
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
