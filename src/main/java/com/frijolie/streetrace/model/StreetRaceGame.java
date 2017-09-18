package com.frijolie.streetrace.model;

import com.frijolie.streetrace.model.cards.Card;
import com.frijolie.streetrace.model.cards.DistanceCard;
import com.frijolie.streetrace.model.cards.DistanceCardType;
import com.frijolie.streetrace.model.cards.HazardCard;
import com.frijolie.streetrace.model.cards.RemedyCard;
import com.frijolie.streetrace.model.cards.SafetyCard;
import com.frijolie.streetrace.model.cards.SafetyCardType;
import com.frijolie.streetrace.model.cards.SpeedCard;
import com.frijolie.streetrace.model.cards.SpeedCardType;
import com.frijolie.streetrace.model.moves.Move;
import java.util.List;
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
    private final int MILE_LIMIT = 1000;

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
            return speedPile.peek();
        }
    }

    @Override
    public Card battlePileTopCard(Player player) {
        Stack<Card> battlePile = player.getTableau().getSpeedPile();
        if(battlePile.isEmpty()) {
            return null;
        } else {
            return player.getTableau().getBattlePile().peek();
        }
    }

    public boolean isValidMove(Player player, Card card) {
        if (card instanceof DistanceCard) {
            if (player.getTableau().isRolling()) {
                // player is rolling
                if (player.getTableau().getTotalMiles() + card.getType().getValue() <= MILE_LIMIT) {
                    // card value + totalMiles cannot exceed MILE_LIMIT
                    if (player.getTableau().hasSpeedLimit()) {
                        // player has speed limit, can only play mile25 and miles50
                        if (card.getType() == DistanceCardType.MILES_25 ||
                                card.getType() == DistanceCardType.MILES_50) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        // player is rolling, does not have a speed limit
                        if (card.getType() == DistanceCardType.MILES_200) {
                            // cannot play more than 2 miles200 cards in a game
                            if(player.getTableau().getPlayed200s() < 2) {
                                player.getTableau().addPlayed200s();
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return true;
                        }
                    }
                } else {
                    return false;
                }
            } else {
                // player is not rolling, cannot play a distance card
                return false;
            }
        } else if (card instanceof SpeedCard) {
            if (card.getType() == SpeedCardType.END_LIMIT) {
                if (player.getTableau().hasSpeedLimit()) {
                    return true;
                } else {
                    return false;
                }
            } else if (card.getType() == SpeedCardType.SPEED_LIMIT) {
                if (player.getOpponentTableau().getBattlePile().isEmpty()) {
                    return true;
                } else if (player.getOpponentTableau().getSpeedPile().peek().getType() == SpeedCardType.END_LIMIT ||
                        player.getOpponentTableau().getSpeedPile().isEmpty()) {
                    return true;
                } else {
                    return false;
                }
            }
        } else if (card instanceof SafetyCard) {
            return true;
        }
        return false;
    }

    public boolean canMoveHere(Player player, Card card, CardLocation destination) {
        card = Objects.requireNonNull(card);
        destination = Objects.requireNonNull(destination);
        player = Objects.requireNonNull(player);

        if (player == computer) {
            if (card instanceof DistanceCard) {
                return destination == CardLocation.COMPUTER_DISTANCE_PILE;
            } else if (card instanceof SpeedCard) {
                if (card.getType() == SpeedCardType.END_LIMIT) {
                    // end limit cards are played on your speed pile
                    return destination == CardLocation.COMPUTER_SPEED_PILE;
                } else if (card.getType() == SpeedCardType.SPEED_LIMIT) {
                    // speed limit cards are played on your opponents speed pile
                    return destination == CardLocation.PLAYER_SPEED_PILE;
                }
            } else if (card instanceof RemedyCard) {
                // remedy cards are played on your tableau
                return destination == CardLocation.COMPUTER_BATTLE_PILE;
            } else if (card instanceof HazardCard) {
                // hazard cards are played on your opponents tableau
                return destination == CardLocation.PLAYER_BATTLE_PILE;
            } else if (card instanceof SafetyCard) {
                return destination == CardLocation.COMPUTER_SAFETY_PILE;
            } else if (destination == CardLocation.DRAW_PILE) {
                return false;
            } else if (destination == CardLocation.DISCARD_PILE) {
                return true;
            } else if (destination == CardLocation.COMPUTER_HAND) {
                return false;
            }
        } else if (player == this.player) {
            if (card instanceof DistanceCard) {
                return destination == CardLocation.PLAYER_DISTANCE_PILE;
            } else if (card instanceof SpeedCard) {
                if (card.getType() == SpeedCardType.END_LIMIT) {
                    // end limit cards are played on your speed pile
                    return destination == CardLocation.PLAYER_SPEED_PILE;
                } else if (card.getType() == SpeedCardType.SPEED_LIMIT) {
                    // speed limit cards are played on your opponents speed pile
                    return destination == CardLocation.COMPUTER_SPEED_PILE;
                }
            } else if (card instanceof RemedyCard) {
                // remedy cards are played on your tableau
                return destination == CardLocation.PLAYER_BATTLE_PILE;
            } else if (card instanceof HazardCard) {
                // hazard cards are played on your opponents tableau
                return destination == CardLocation.COMPUTER_BATTLE_PILE;
            } else if (card instanceof SafetyCard) {
                return destination == CardLocation.COMPUTER_SAFETY_PILE;
            } else if (destination == CardLocation.DRAW_PILE) {
                return false;
            } else if (destination == CardLocation.DISCARD_PILE) {
                return true;
            } else if (destination == CardLocation.PLAYER_HAND) {
                return false;
            }
        }
        // don't know how this could ever be reached...
        // the IDE is complaining so here it is...
        System.err.println("Player must be equal to neither \'computer\' or \'player\'");
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

    public boolean safetyPileContains(Player player, SafetyCardType type) {
        List<SafetyCard> safetyPile = player.getTableau().getSafetyPile();
        for (SafetyCard card : safetyPile) {
            if (card.getType() == type) {
                return true;
            }
        }
        return false;
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