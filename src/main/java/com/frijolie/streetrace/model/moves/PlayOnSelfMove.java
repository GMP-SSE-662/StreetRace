package com.frijolie.streetrace.model.moves;

public class PlayOnSelfMove implements Move {

    @Override
    public void perform() {
        // TODO take from hand place it in a valid pile on playerTableau
    }

    @Override
    public void undo() {
        // TODO take from pile on playerTableau and return it to the hand
    }

}