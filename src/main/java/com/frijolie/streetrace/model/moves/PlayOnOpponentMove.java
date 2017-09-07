package com.frijolie.streetrace.model.moves;

public class PlayOnOpponentMove implements Move {

    @Override
    public void perform() {
        // TODO take from playerHand and place it in valid opponent tableau pile
    }

    @Override
    public void undo() {
        // TODO take from opponent tableau pile and return to player hand
    }

}