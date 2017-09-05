package com.frijolie.streetrace.model;

public interface Card<T extends CardType> {

    public T getType();

}
