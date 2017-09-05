package com.frijolie.streetrace.model;

import java.util.Objects;

public class Player {

    private final Hand hand;
    private final Tableau tableau;
    private int score;
    private Tableau opponentsTableau;

    public Player() {
        hand = new Hand();
        tableau = new Tableau();
    }

    public Hand getHand() {
        return hand;
    }

    public Tableau getTableau() {
        return tableau;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int value) {
        int amount = Objects.requireNonNull(value, "You cannot add a null value to Player score");
        if (amount >= 0) {
            score += amount;
        } else {
            System.err.println("You cannot add a negative score");
        }
    }
    
    public void setOpponentsTableau(Tableau tableau) {
        opponentsTableau = tableau;
    }

}
