package com.frijolie.streetrace.controller;

import com.frijolie.streetrace.model.cards.Card;
import com.frijolie.streetrace.model.Deck;
import com.frijolie.streetrace.model.Hand;
import com.frijolie.streetrace.model.Player;
import com.frijolie.streetrace.model.Tableau;
import java.util.ArrayDeque;
import java.util.Deque;

public class StreetRace {

    private int playerTotalMiles = 0;
    private int computerTotalMiles = 0;
    private final Player player;
    private final Player computer;
    private final Tableau playerTableau;
    private final Tableau computerTableau;
    private final Deque<Card> discardPile;
    private final Deque<Card> drawPile;
    private final Deck deck;
    private final Hand playerHand;
    private final Hand computerHand;

    public StreetRace() {
        player = new Player();
        computer = new Player();
        playerTableau = player.getTableau();
        computerTableau = computer.getTableau();
        playerHand = player.getHand();
        computerHand = computer.getHand();
        discardPile = new ArrayDeque<>();
        deck = new Deck();
        deck.shuffle(5);
        drawPile = new ArrayDeque<>();
        drawPile.addAll(deck.getDeck());
        player.setOpponentsTableau(computerTableau);
        computer.setOpponentsTableau(playerTableau);
    }

    public void startGame() {
        deal();
    }

    public void deal() {
        for (int i = 0; i < 7; i++) {
            playerHand.addCard(drawPile.removeLast());
            computerHand.addCard(drawPile.removeLast());
        }
    }

    public void deal(Player player) {
        player.getHand().addCard(drawPile.removeLast());
    }

}