package com.frijolie.streetrace.model;

import com.frijolie.streetrace.model.cards.Card;
import com.frijolie.streetrace.model.moves.Move;
import java.util.Objects;
import java.util.Stack;

public class StreetRaceGame implements StreetRaceGameHelper {

    private static StreetRaceGame instance = null;

    private Stack<Move> moves;

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
    private CardLocation location;

    private StreetRaceGame() {
        moves = new Stack<>();
        player = new Player();
        computer = new Player();
        playerTableau = player.getTableau();
        computerTableau = computer.getTableau();
        playerHand = player.getHand();
        computerHand = computer.getHand();
        discardPile = new Stack<>();
        deck = new Deck();
        drawPile = new Stack<>();
    }

    public static StreetRaceGame getInstance() {
        if (instance == null) {
            instance = new StreetRaceGame();
        }
        return instance;
    }

    public void startGame() {
        player.setOpponentsTableau(computerTableau);
        computer.setOpponentsTableau(playerTableau);
        deck.shuffle(5);
        drawPile.addAll(deck.getList());
        deal();
    }

    public void deal() {
        for (int i = 0; i < 7; i++) {
            playerHand.addCard(drawPile.pop());
            computerHand.addCard(drawPile.pop());
        }
    }

    public void reset() {
        moves.clear();
        discardPile.clear();
        computerHand.clear();
        computerTableau.clear();
        playerHand.clear();
        playerTableau.clear();
        drawPile.clear();
        deck.clear();
        deck.populate();
        deck.shuffle(5);
    }

    public void addToDiscardPile(Card card) {
        discardPile.add(Objects.requireNonNull(card));
    }

    @Override
    public boolean isDrawPileEmtpy() {
        return drawPile.isEmpty();
     }

    @Override
    public Card speedPileTopCard(Player player) {
        Stack<Card> speedPile = player.getTableau().getSpeedPile();
        if(speedPile.isEmpty()) {
            return null;
        } else {
            Card card = speedPile.peek();
            return card;
        }
    }

    @Override
    public Card battlePileTopCard(Player player) {
        Stack<Card> battlePile = player.getTableau().getSpeedPile();
        if(battlePile.isEmpty()) {
            return null;
        } else {
            Card card = player.getTableau().getBattlePile().peek();
            return card;
        }
    }

    @Override
    public boolean isValidMove() {
        // TODO
        return false;
    }

    @Override
    public Move getDiscardMove() {
        // TODO
        return null;
    }

    @Override
    public Move getPlayOnOpponentMove() {
       // TODO
       return null;
    }

    @Override
    public Move getPlayOnSelfMove() {
        // TODO
        return null;
    }

    public CardLocation getLocation(Card card) {

        if (playerHand.getList().contains(card)) {
            return CardLocation.PLAYER_HAND;
        } else if (playerTableau.getDistancePile().contains(card)) {
            return CardLocation.PLAYER_DISTANCE_PILE;
        } else if (playerTableau.getSpeedPile().contains(card)) {
            return CardLocation.PLAYER_SPEED_PILE;
        } else if (playerTableau.getSafetyPile().contains(card)) {
            return CardLocation.PLAYER_SAFETY_PILE;
        } else if (playerTableau.getBattlePile().contains(card)) {
            return CardLocation.PLAYER_BATTLE_PILE;
        } else if (computerHand.getList().contains(card)) {
            return CardLocation.COMPUTER_HAND;
        } else if (computerTableau.getDistancePile().contains(card)) {
            return CardLocation.COMPUTER_DISTANCE_PILE;
        } else if (computerTableau.getSpeedPile().contains(card)) {
            return CardLocation.COMPUTER_SPEED_PILE;
        } else if (computerTableau.getSafetyPile().contains(card)) {
            return CardLocation.COMPUTER_SAFETY_PILE;
        } else if (computerTableau.getBattlePile().contains(card)) {
            return CardLocation.COMPUTER_BATTLE_PILE;
        } else if (discardPile.contains(card)) {
            return CardLocation.DISCARD_PILE;
        } else if (drawPile.contains(card)) {
            return CardLocation.DRAW_PILE;
        } else {
            return CardLocation.UNKNOWN;
        }
    }

    public Stack<Move> getMoves() {
        return moves;
    }

    public int getPlayerTotalMiles() {
        return playerTotalMiles;
    }

    public int getComputerTotalMiles() {
        return computerTotalMiles;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getComputer() {
        return computer;
    }

    public Tableau getPlayerTableau() {
        return playerTableau;
    }

    public Tableau getComputerTableau() {
        return computerTableau;
    }

    public Stack<Card> getDiscardPile() {
        return discardPile;
    }

    public Stack<Card> getDrawPile() {
        return drawPile;
    }

    public Deck getDeck() {
        return deck;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public Hand getComputerHand() {
        return computerHand;
    }

}