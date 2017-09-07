package com.frijolie.streetrace.model;

import com.frijolie.streetrace.model.Deck;
import com.frijolie.streetrace.model.cards.CardType;
import com.frijolie.streetrace.model.cards.DistanceCardType;
import com.frijolie.streetrace.model.cards.HazardCardType;
import com.frijolie.streetrace.model.cards.RemedyCardType;
import com.frijolie.streetrace.model.cards.SafetyCardType;
import com.frijolie.streetrace.model.cards.SpeedCardType;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DeckTest {

    Map<CardType, Integer> frequency;
    Deck instance;

    @Before
    public void setUp() {
        instance = new Deck();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testDeck_ContainsCorrectNumberOfCards() {
        assertTrue(instance.getList().size() == 106);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfAccidentCards() {
        assertTrue("There are not exactly 3 ACCIDENT cards in the deck", getFrequency(HazardCardType.ACCIDENT) == 3);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfOutOfGasCards() {
        assertTrue("There are not exactly 3 OUT_OF_GAS cards in the deck", getFrequency(HazardCardType.OUT_OF_GAS) == 3);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfFlatTireCards() {
        assertTrue("There are not exactly 3 FLAT_TIRE cards in the deck", getFrequency(HazardCardType.FLAT_TIRE) == 3);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfStopCards() {
        assertTrue("There are not exactly 5 STOP cards in the deck", getFrequency(HazardCardType.STOP) == 5);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfSpeedLimitCards() {
        assertTrue("There are not exactly 4 SPEED_LIMIT cards in the deck", getFrequency(SpeedCardType.SPEED_LIMIT) == 4);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfRepairCards() {
        assertTrue("There are not exactly 6 REPAIR cards in the deck", getFrequency(RemedyCardType.REPAIR) == 6);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfGasolineCards() {
        assertTrue("There are not exactly 6 GASOLINE cards in the deck", getFrequency(RemedyCardType.GASOLINE) == 6);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfSpareTireCards() {
        assertTrue("There are not exactly 6 SPARE_TIRE cards in the deck", getFrequency(RemedyCardType.SPARE_TIRE) == 6);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfRollCards() {
        assertTrue("There are not exactly 14 ROLL cards in the deck", getFrequency(RemedyCardType.ROLL) == 14);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfEndLimitCards() {
        assertTrue("There are not exactly 6 END_OF_LIMIT cards in the deck", getFrequency(SpeedCardType.END_LIMIT) == 6);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfDrivingAceCards() {
        assertTrue("There are not exactly 1 DRIVING_ACE card in the deck", getFrequency(SafetyCardType.DRIVING_ACE) == 1);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfExtraTankCards() {
        assertTrue("There is not exactly 1 EXTRA_TANK card in the deck", getFrequency(SafetyCardType.EXTRA_TANK) == 1);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfPunctureProofCards() {
        assertTrue("There is not exactly 1 PUNCTURE_PROOF card in the deck", getFrequency(SafetyCardType.PUNCTURE_PROOF) == 1);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfRightOfWayCards() {
        assertTrue("There is not exactly 1 RIGHT_OF_WAY card in the deck", getFrequency(SafetyCardType.RIGHT_OF_WAY) == 1);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfMiles25Cards() {
        assertTrue("There are not exactly 10 MILES_25 cards in the deck", getFrequency(DistanceCardType.MILES_25) == 10);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfMiles50Cards() {
        assertTrue("There are not exactly 10 MILES_50 cards in the deck", getFrequency(DistanceCardType.MILES_50) == 10);
    }

    @Test
    public void testDeck_ContainsCorrectAmountOfMiles75Cards() {
        assertTrue("There are not exactly 10 MILES_75 cards in the deck", getFrequency(DistanceCardType.MILES_75) == 10);
    }

    @Test
    public void testDeck_ContainsCorrectAmountofMiles100Cards() {
        assertTrue("There are not exactly 12 MILES_100 cards in the deck", getFrequency(DistanceCardType.MILES_100) == 12);
    }

    @Test
    public void testDeck_ContainsCorrectAmountofMiles200Cards() {
        assertTrue("There are not exactly 4 MILES_200 cards in the deck", getFrequency(DistanceCardType.MILES_200) == 4);
    }

    public int getFrequency(CardType type) {
        if (frequency == null) {
            calculateFrequency();
        }
        return frequency.get(type);
    }

    public void calculateFrequency() {
        Deck instance = new Deck();
        frequency = new HashMap<>();

        instance.getList().stream().map((card) -> card.getType()).forEachOrdered((type) -> {
            Integer count = frequency.get(type);

            if (count == null) {
                count = 0;
            }
            count++;
            frequency.put(type, count);
        });
    }

}
