package com.frijolie.streetrace.model.cards;

import java.util.Objects;

public class SafetyCard implements Card {

    private final SafetyCardType type;

    public SafetyCard(SafetyCardType type) {
        this.type = Objects.requireNonNull(type, "SafetyCardType cannot be null");
    }

    @Override
    public CardType getType() {
        return type;
    }

}
