package com.frijolie.streetrace.model.cards;

import java.util.Objects;

public class DistanceCard implements Card {

    private final DistanceCardType type;

    public DistanceCard(DistanceCardType type) {
        this.type = Objects.requireNonNull(type, "DistanceCardType must not be null");
    }

    @Override
    public String getName() {
        return getType().getName();
    }

    @Override
    public CardType getType() {
        return type;
    }

}
