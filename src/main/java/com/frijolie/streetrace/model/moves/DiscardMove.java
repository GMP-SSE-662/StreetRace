package com.frijolie.streetrace.model.moves;

public class DiscardMove implements Move {

    @Override
    public void perform() {
        // TODO take out of the hand
        // TODO place in the discard pile
    }

    @Override
    public void undo() {
        // TODO take out of discard pile
        // TODO place it in the hand
    }

}