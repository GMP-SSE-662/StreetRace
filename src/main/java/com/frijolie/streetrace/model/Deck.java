package com.frijolie.streetrace.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> deck;

    public Deck() {
        deck = new ArrayList<>();
        populate();
    }

    public void populate() {

        for (int i = 0; i < 14; i++) {
            deck.add(new RemedyCard(RemedyCardType.ROLL));
        }

        for (int i = 0; i < 12; i++) {
            deck.add(new DistanceCard(DistanceCardType.MILES_100));
        }

        for (int i = 0; i < 10; i++) {
            deck.add(new DistanceCard(DistanceCardType.MILES_25));
            deck.add(new DistanceCard(DistanceCardType.MILES_50));
            deck.add(new DistanceCard(DistanceCardType.MILES_75));
        }

        for (int i = 0; i < 6; i++) {
            deck.add(new RemedyCard(RemedyCardType.REPAIR));
            deck.add(new RemedyCard(RemedyCardType.GASOLINE));
            deck.add(new RemedyCard(RemedyCardType.SPARE_TIRE));
            deck.add(new SpeedCard(SpeedCardType.END_LIMIT));
        }

        for (int i = 0; i < 5; i++) {
            deck.add(new HazardCard(HazardCardType.STOP));
        }

        for (int i = 0; i < 4; i++) {
            deck.add(new SpeedCard(SpeedCardType.SPEED_LIMIT));
            deck.add(new DistanceCard(DistanceCardType.MILES_200));
        }

        for (int i = 0; i < 3; i++) {
            deck.add(new HazardCard(HazardCardType.ACCIDENT));
            deck.add(new HazardCard(HazardCardType.OUT_OF_GAS));
            deck.add(new HazardCard(HazardCardType.FLAT_TIRE));
        }

        deck.add(new SafetyCard(SafetyCardType.DRIVING_ACE));
        deck.add(new SafetyCard(SafetyCardType.EXTRA_TANK));
        deck.add(new SafetyCard(SafetyCardType.PUNCTURE_PROOF));
        deck.add(new SafetyCard(SafetyCardType.RIGHT_OF_WAY));

    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public void shuffle(int numOfTimes) {
        for (int i = 0; i < numOfTimes; i++) {
            Collections.shuffle(deck);
        }
    }

    public void display() {
        for (Card card : deck) {
            System.out.println(card.getType());
        }
    }

    public List<Card> getDeck() {
        return deck;
    }

}
