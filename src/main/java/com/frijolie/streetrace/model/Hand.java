package com.frijolie.streetrace.model;

import com.frijolie.streetrace.model.cards.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Hand {

    private List<Card> list;
    private ObservableList<Card> hand;

    public Hand() {
        list = new ArrayList<>();
        hand = FXCollections.observableList(list);
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

    public ObservableList<Card> getList() {
        return hand;
    }

    public void displayHand() {
        int index = 0;
        for (Card card : list) {
            System.out.println("[" + index++ + "] " + card.getType());
        }
    }

    public void clear() {
        list.clear();
    }

}