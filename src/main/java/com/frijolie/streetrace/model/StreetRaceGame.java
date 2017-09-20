package com.frijolie.streetrace.model;

import com.frijolie.streetrace.model.cards.BattleCard;
import com.frijolie.streetrace.model.cards.Card;
import com.frijolie.streetrace.model.cards.DistanceCard;
import com.frijolie.streetrace.model.cards.DistanceCardType;
import com.frijolie.streetrace.model.cards.HazardCard;
import com.frijolie.streetrace.model.cards.HazardCardType;
import com.frijolie.streetrace.model.cards.RemedyCard;
import com.frijolie.streetrace.model.cards.RemedyCardType;
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
        boolean validMove = false;
        if (card instanceof DistanceCard) {
            validMove = checkValidDistanceCardMoves(player, card);
        } else if (card instanceof SpeedCard) {
            validMove = checkValidSpeedCardMoves(player, card);
        } else if (card instanceof SafetyCard) {
            validMove = true;
        } else if (card instanceof BattleCard) {
            validMove = checkValidBattleCardMoves(player, card);
        }
        return validMove;
    }

    public boolean checkValidDistanceCardMoves(Player player, Card card) {
        boolean validMove = false;
        if (player.getTableau().isRolling()) {
            if (player.getTableau().getTotalMiles() + card.getType().getValue() <= MILE_LIMIT) {
                if (player.getTableau().hasSpeedLimit()) {
                    if (card.getType() == DistanceCardType.MILES_25) {
                        validMove = true;
                    } else if (card.getType() == DistanceCardType.MILES_50) {
                        validMove = true;
                    } else {
                        validMove = false;
                    }
                } else if (card.getType() == DistanceCardType.MILES_200) {
                        if (player.getTableau().getPlayed200s() < 2) {
                            player.getTableau().addPlayed200s();
                            validMove = true;
                        } else {
                            validMove = false;
                        }
                } else {
                    validMove = true;
                }
            } else {
                validMove = false;
            }
        } else {
            validMove = false;
        }
        return validMove;
    }

    public boolean checkValidSpeedCardMoves(Player player, Card card) {
        boolean validMove = false;
        if (card.getType() == SpeedCardType.END_LIMIT) {
            if (player.getTableau().hasSpeedLimit()) {
                validMove = true;
            } else {
                validMove = false;
            }
        } else if (card.getType() == SpeedCardType.SPEED_LIMIT) {
            if (player.getOpponentTableau().safetyPileContains(SafetyCardType.RIGHT_OF_WAY)) {
                validMove = false;
            } else if (player.getOpponentTableau().getSpeedPile().isEmpty()) {
                validMove = true;
            } else if (player.getOpponentTableau().getBattlePile().isEmpty()) {
                if (player.getOpponentTableau().getSpeedPile().peek().getType() == SpeedCardType.SPEED_LIMIT) {
                    validMove = false;
                } else {
                    validMove = true;
                }
            } else if (player.getOpponentTableau().getSpeedPile().peek().getType() == SpeedCardType.SPEED_LIMIT) {
                validMove = false;
            } else {
                // opponent top card in speed pile must be an END_LIMIT
                validMove = true;
            }
        }
        return validMove;
    }

    public boolean checkValidBattleCardMoves(Player player, Card card) {
        boolean validMove = false;
        if (card instanceof HazardCard) {
            if (player.getOpponentTableau().isRolling()) {
                if (card.getType() == HazardCardType.ACCIDENT) {
                    if (player.getOpponentTableau().safetyPileContains(SafetyCardType.DRIVING_ACE)) {
                        validMove = false;
                    } else {
                        validMove = true;
                    }
                } else if (card.getType() == HazardCardType.FLAT_TIRE) {
                    if (player.getOpponentTableau().safetyPileContains(SafetyCardType.PUNCTURE_PROOF)) {
                        validMove = false;
                    } else {
                        validMove = true;
                    }
                } else if (card.getType() == HazardCardType.OUT_OF_GAS) {
                    if (player.getOpponentTableau().safetyPileContains(SafetyCardType.EXTRA_TANK)) {
                        validMove = false;
                    } else {
                        validMove = true;
                    }
                } else if (card.getType() == HazardCardType.STOP) {
                    if (player.getOpponentTableau().safetyPileContains(SafetyCardType.RIGHT_OF_WAY)) {
                        validMove = false;
                    } else {
                        validMove = true;
                    }
                }
            } else {
                validMove = false;
            }
        } else if (card instanceof RemedyCard) {
            if (player.getTableau().getBattlePile().isEmpty()) {
                validMove = false;
            } else {
                if (card.getType() == RemedyCardType.GASOLINE) {
                    if (player.getTableau().getBattlePileTopCard().getType() == HazardCardType.OUT_OF_GAS) {
                        validMove = true;
                    } else {
                        validMove = false;
                    }
                } else if (card.getType() == RemedyCardType.REPAIR) {
                    if (player.getTableau().getBattlePileTopCard().getType() == HazardCardType.ACCIDENT) {
                        validMove = true;
                    } else {
                        validMove = false;
                    }
                } else if (card.getType() == RemedyCardType.ROLL) {
                    if (player.getTableau().getBattlePileTopCard().getType() == RemedyCardType.GASOLINE) {
                        validMove = true;
                    } else if (player.getTableau().getBattlePileTopCard().getType() == RemedyCardType.REPAIR) {
                        validMove = true;
                    } else if (player.getTableau().getBattlePileTopCard().getType() == RemedyCardType.SPARE_TIRE) {
                        validMove = true;
                    } else if (player.getTableau().getBattlePileTopCard().getType() == RemedyCardType.ROLL) {
                        validMove = false;
                    } else {
                        validMove = false;
                    }
                } else if (card.getType() == RemedyCardType.SPARE_TIRE) {
                    if (player.getTableau().getBattlePileTopCard().getType() == HazardCardType.FLAT_TIRE) {
                        validMove = true;
                    } else {
                        validMove = false;
                    }
                }
            }
        }
        return validMove;
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