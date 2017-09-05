package com.frijolie.streetrace.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hand {

    private List<Card> hand;

    public Hand() {
        hand = new ArrayList<>();
    }

    public boolean addCard(Card card) {
        Objects.requireNonNull(card, "You cannot add a null card to the hand");
        return hand.add(card);
    }

    public boolean removeCard(Card card) {
        Objects.requireNonNull(card, "You cannot remove a null card from the hand");
        if (hand.contains(card)) {
            return hand.remove(card);
        } else {
            // TODO handle removeCard if it doesn't exist
            return false;
        }
    }

    public List<Card> getHand() {
        return hand;
    }

    public void displayHand() {
        int index = 0;
        for (Card card : hand) {
            System.out.println("[" + index++ + "] " + card.getType());
        }
    }

}
