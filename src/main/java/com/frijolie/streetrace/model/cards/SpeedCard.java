package com.frijolie.streetrace.model.cards;

import java.util.Objects;

public class SpeedCard implements Card {

    private final SpeedCardType type;

    public SpeedCard(SpeedCardType type) {
        this.type = Objects.requireNonNull(type, "SpeedCardType must not be null");
    }

    @Override
    public CardType getType() {
        return type;
    }

    @Override
    public String getName() {
        return getType().getName();
    }
}
