package com.frijolie.streetrace.model;

import com.frijolie.streetrace.model.cards.BattleCard;
import com.frijolie.streetrace.model.cards.Card;
import com.frijolie.streetrace.model.cards.CardType;
import com.frijolie.streetrace.model.cards.DistanceCard;
import com.frijolie.streetrace.model.cards.DistanceCardType;
import com.frijolie.streetrace.model.cards.HazardCard;
import com.frijolie.streetrace.model.cards.RemedyCardType;
import com.frijolie.streetrace.model.cards.SafetyCard;
import com.frijolie.streetrace.model.cards.SpeedCard;
import com.frijolie.streetrace.model.cards.SpeedCardType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Tableau {

    private final List<DistanceCard> distancePile;
    private final List<SafetyCard> safetyPile;
    private final Stack<Card> speedPile;
    private final Stack<BattleCard> battlePile;
    private int totalMiles = 0;
    private int played200s = 0;

    public Tableau() {
        distancePile = new ArrayList<>();
        safetyPile = new ArrayList<>();
        speedPile = new Stack<>();
        battlePile = new Stack<>();
    }

    public int calculateTotalMiles() {
        int miles = 0;
        for (DistanceCard distanceCard : distancePile) {
            miles += distanceCard.getType().getValue();
        }
        return miles;
    }

    public boolean addToDistancePile(DistanceCard card) {
        // should only contain MILES_25, MILES_50, MILES_75, MILES_100, and MILES_200 cards
        // TODO cannot play more than 2 MILES_200 cards--have to discard the rest
        card = Objects.requireNonNull(card, "You can\'t add a null value card to the Distance Pile");
        CardType type = card.getType();
        if(type == DistanceCardType.MILES_200) {
            if (played200s < 2) {
                played200s++;
                return distancePile.add(card);
            } else {
                System.err.println("You cannot play 2 or more 200 Miles cards in the Distance Pile.");
                return false;
            }
        } else {
            return distancePile.add(card);
        }
    }

    public boolean addToSafetyPile(SafetyCard card) {
        // should only contain DRIVING_ACE, EXTRA_TANK, PUNCTURE_PROOF, and RIGHT_OF_WAY cards
        return safetyPile.add(Objects.requireNonNull(card,
            "You can\'t add a null value card to the Safety Pile"));
    }

    public void addToSpeedPile(SpeedCard card) {
        // should only contain SPEED_LIMT and END_LIMIT cards
        speedPile.push(Objects.requireNonNull(card,
            "You can\'t add a null value card to the Speed Pile"));
    }

    public void addToBattlePile(BattleCard card) {
        // should only contain ACCIDENT, OUT_OF_GAS, FLAT_TIRE, STOP, REPAIR, GASOLINE, SPARE_TIRE,
        // and ROLL cards
        battlePile.push(Objects.requireNonNull(card,
            "You can\'t add a null value card to the Battle Pile"));
    }

    public boolean isRolling() {

        if (battlePile.isEmpty()) {
            return false;
        }

        CardType topCard = battlePile.peek().getType();
        return topCard == RemedyCardType.ROLL;
    }

    public boolean hasHazard() {
        if (battlePile.isEmpty()) {
            return false;
        }
        Card topCard = getBattlePileTopCard();
        return topCard instanceof HazardCard;
    }

    public boolean hasSpeedLimit() {
        if (speedPile.isEmpty()) {
            return false;
        }
        CardType topCard = speedPile.peek().getType();
        return topCard == SpeedCardType.SPEED_LIMIT;
    }

    public List<DistanceCard> getDistancePile() {
        return distancePile;
    }

    public List<SafetyCard> getSafetyPile() {
        return safetyPile;
    }

    public Stack<Card> getSpeedPile() {
        return speedPile;
    }

    public Stack<BattleCard> getBattlePile() {
        return battlePile;
    }

    public int getTotalMiles() {
        return totalMiles;
    }

    public int getPlayed200s() {
        return played200s;
    }

    public Card getBattlePileTopCard() {
        return battlePile.peek();
    }

}