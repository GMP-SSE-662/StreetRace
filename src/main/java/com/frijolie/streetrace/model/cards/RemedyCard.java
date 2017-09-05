package com.frijolie.streetrace.model.cards;

import com.frijolie.streetrace.model.cards.BattleCard;
import java.util.Objects;

public class RemedyCard extends BattleCard implements Card {

    private final RemedyCardType type;

    public RemedyCard(RemedyCardType type) {
        this.type = Objects.requireNonNull(type, "RemedyCardType must not be null");
    }

    @Override
    public CardType getType() {
        return type;
    }
}
