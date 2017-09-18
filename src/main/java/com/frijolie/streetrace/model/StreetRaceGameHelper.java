package com.frijolie.streetrace.model;

import com.frijolie.streetrace.model.cards.Card;
import com.frijolie.streetrace.model.moves.Move;

public interface StreetRaceGameHelper {

    public boolean isDrawPileEmtpy();

    public Card speedPileTopCard(Player player);

    public Card battlePileTopCard(Player player);

    public Move getDiscardMove();

    public Move getPlayOnOpponentMove();

    public Move getPlayOnSelfMove();

}