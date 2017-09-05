package com.frijolie.streetrace.model;

public enum SpeedCardType implements CardType {

    SPEED_LIMIT(0,"/images/speedlimit.png"),
    END_LIMIT(0,"/images/endlimit.png");

    private final int value;
    private final String path;

    private SpeedCardType(int value, String path) {
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
