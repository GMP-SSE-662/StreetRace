package com.frijolie.streetrace.model.moves;

public interface Move {

    public void perform();

    public void undo();

    public boolean isValid();

}