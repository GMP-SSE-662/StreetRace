package com.frijolie.streetrace.controller;

import com.frijolie.streetrace.model.Deck;
import com.frijolie.streetrace.model.Hand;
import com.frijolie.streetrace.model.Player;
import com.frijolie.streetrace.model.Tableau;
import com.frijolie.streetrace.model.cards.Card;
import java.util.Stack;

public class StreetRace {

    private int playerTotalMiles = 0;
    private int computerTotalMiles = 0;
    private final Player player;
    private final Player computer;
    private final Tableau playerTableau;
    private final Tableau computerTableau;
    private final Stack<Card> discardPile;
    private final Stack<Card> drawPile;
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
        discardPile = new Stack<>();
        deck = new Deck();
        deck.shuffle(5);
        drawPile = new Stack<>();
        drawPile.addAll(deck.getDeck());
        player.setOpponentsTableau(computerTableau);
        computer.setOpponentsTableau(playerTableau);
    }

    public void startGame() {
        deal();
    }

    public void deal() {
        for (int i = 0; i < 7; i++) {
            playerHand.addCard(drawPile.pop());
            computerHand.addCard(drawPile.pop());
        }
    }

    public void deal(Player player) {
        player.getHand().addCard(drawPile.pop());
    }

}