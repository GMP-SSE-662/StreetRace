package com.frijolie.streetrace.model;

import com.frijolie.streetrace.model.cards.Card;
import java.util.Objects;
import javafx.collections.ObservableList;

public class Player {

    private final Hand hand;
    private final Tableau tableau;
    private Tableau opponentTableau;
    private int score;

    public Player() {
        hand = new Hand();
        tableau = new Tableau();
    }

    public ObservableList<Card> getHand() {
        return hand.getList();
    }

    public Tableau getOpponentTableau() {
        return opponentTableau;
    }

    public void setOpponentsTableau(Tableau tableau) {
        opponentTableau = Objects.requireNonNull(tableau);
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

}
