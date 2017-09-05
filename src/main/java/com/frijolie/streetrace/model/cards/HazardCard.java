package com.frijolie.streetrace.model.cards;

import com.frijolie.streetrace.model.cards.BattleCard;
import java.util.Objects;

public class HazardCard extends BattleCard implements Card {

    private final HazardCardType type;

    public HazardCard(HazardCardType type) {
        this.type = Objects.requireNonNull(type, "HazardCardType must not be null");
    }

    @Override
    public CardType getType() {
        return type;
    }

}
