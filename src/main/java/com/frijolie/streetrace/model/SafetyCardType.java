package com.frijolie.streetrace.model;

public enum SafetyCardType implements CardType {
    DRIVING_ACE(100,"/images/drivingace.png"),
    EXTRA_TANK(100,"/images/extratank.png"),
    PUNCTURE_PROOF(100,"/images/punctureproof.png"),
    RIGHT_OF_WAY(100,"/images/rightofway.png");

    private final int value;
    private final String path;

    private SafetyCardType(int value, String path) {
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
