package com.frijolie.streetrace.model.cards;

public interface Card<T extends CardType> {

    public T getType();

}
