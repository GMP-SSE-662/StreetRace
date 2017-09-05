import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import com.frijolie.streetrace.model.cards.CardType;
import com.frijolie.streetrace.model.Deck;
import com.frijolie.streetrace.model.cards.DistanceCardType;
import com.frijolie.streetrace.model.cards.HazardCardType;
import com.frijolie.streetrace.model.cards.RemedyCardType;
import com.frijolie.streetrace.model.cards.SafetyCardType;
import com.frijolie.streetrace.model.cards.SpeedCardType;

public class DeckTest {

    Map<CardType, Integer> frequency;

    @Test
    public void testPopulate() {
        Deck instance = new Deck();
        instance.populate();
    }

    @Test
    public void testDeckContainsCorrectNumberOfCards() {
        Deck instance = new Deck();
        assertTrue(instance.getDeck().size() == 106);
        if (instance.getDeck().size() == 106) {
            System.out.println("The deck contains the correct number of cards (106)");
        } else {
            System.err.println("The deck does not contain 106 cards");
        }
    }

    @Test
    public void testDeckContainsCorrectAmountOfSpecificCards() {
        Map<CardType, Integer> correctCardCounts = new HashMap<>();
        correctCardCounts.put(HazardCardType.ACCIDENT, 3);
        correctCardCounts.put(HazardCardType.OUT_OF_GAS, 3);
        correctCardCounts.put(HazardCardType.FLAT_TIRE, 3);
        correctCardCounts.put(HazardCardType.STOP, 5);
        correctCardCounts.put(SpeedCardType.SPEED_LIMIT, 4);
        correctCardCounts.put(RemedyCardType.REPAIR, 6);
        correctCardCounts.put(RemedyCardType.GASOLINE, 6);
        correctCardCounts.put(RemedyCardType.SPARE_TIRE, 6);
        correctCardCounts.put(RemedyCardType.ROLL, 14);
        correctCardCounts.put(SpeedCardType.END_LIMIT, 6);
        correctCardCounts.put(SafetyCardType.DRIVING_ACE, 1);
        correctCardCounts.put(SafetyCardType.EXTRA_TANK, 1);
        correctCardCounts.put(SafetyCardType.PUNCTURE_PROOF, 1);
        correctCardCounts.put(SafetyCardType.RIGHT_OF_WAY, 1);
        correctCardCounts.put(DistanceCardType.MILES_25, 10);
        correctCardCounts.put(DistanceCardType.MILES_50, 10);
        correctCardCounts.put(DistanceCardType.MILES_75, 10);
        correctCardCounts.put(DistanceCardType.MILES_100, 12);
        correctCardCounts.put(DistanceCardType.MILES_200, 4);

        assertTrue("There are not exactly 3 ACCIDENT cards in the deck", getFrequency(HazardCardType.ACCIDENT) == 3);
        assertTrue("There are not exactly 3 OUT_OF_GAS cards in the deck", getFrequency(HazardCardType.OUT_OF_GAS) == 3);
        assertTrue("There are not exactly 3 FLAT_TIRE cards in the deck", getFrequency(HazardCardType.FLAT_TIRE) == 3);
        assertTrue("There are not exactly 5 STOP cards in the deck", getFrequency(HazardCardType.STOP) == 5);
        assertTrue("There are not exactly 4 SPEED_LIMIT cards in the deck", getFrequency(SpeedCardType.SPEED_LIMIT) == 4);
        assertTrue("There are not exactly 6 REPAIR cards in the deck", getFrequency(RemedyCardType.REPAIR) == 6);
        assertTrue("There are not exactly 6 GASOLINE cards in the deck", getFrequency(RemedyCardType.GASOLINE) == 6);
        assertTrue("There are not exactly 6 SPARE_TIRE cards in the deck", getFrequency(RemedyCardType.SPARE_TIRE) == 6);
        assertTrue("There are not exactly 14 ROLL cards in the deck", getFrequency(RemedyCardType.ROLL) == 14);
        assertTrue("There are not exactly 6 END_OF_LIMIT cards in the deck", getFrequency(SpeedCardType.END_LIMIT) == 6);
        assertTrue("There are not exactly 1 DRIVING_ACE card in the deck", getFrequency(SafetyCardType.DRIVING_ACE) == 1);
        assertTrue("There is not exactly 1 EXTRA_TANK card in the deck", getFrequency(SafetyCardType.EXTRA_TANK) == 1);
        assertTrue("There is not exactly 1 PUNCTURE_PROOF card in the deck", getFrequency(SafetyCardType.PUNCTURE_PROOF) == 1);
        assertTrue("There is not exactly 1 RIGHT_OF_WAY card in the deck", getFrequency(SafetyCardType.RIGHT_OF_WAY) == 1);
        assertTrue("There are not exactly 10 MILES_25 cards in the deck", getFrequency(DistanceCardType.MILES_25) == 10);
        assertTrue("There are not exactly 10 MILES_50 cards in the deck", getFrequency(DistanceCardType.MILES_50) == 10);
        assertTrue("There are not exactly 10 MILES_75 cards in the deck", getFrequency(DistanceCardType.MILES_75) == 10);
        assertTrue("There are not exactly 12 MILES_100 cards in the deck", getFrequency(DistanceCardType.MILES_100) == 12);
        assertTrue("There are not exactly 4 MILES_200 cards in the deck", getFrequency(DistanceCardType.MILES_200) == 4);

        if (getFrequency(HazardCardType.ACCIDENT) == 3
            && getFrequency(HazardCardType.OUT_OF_GAS) == 3
            && getFrequency(HazardCardType.FLAT_TIRE) == 3
            && getFrequency(HazardCardType.STOP) == 5
            && getFrequency(SpeedCardType.SPEED_LIMIT) == 4
            && getFrequency(RemedyCardType.REPAIR) == 6
            && getFrequency(RemedyCardType.GASOLINE) == 6
            && getFrequency(RemedyCardType.SPARE_TIRE) == 6
            && getFrequency(RemedyCardType.ROLL) == 14
            && getFrequency(SpeedCardType.END_LIMIT) == 6
            && getFrequency(SafetyCardType.DRIVING_ACE) == 1
            && getFrequency(SafetyCardType.EXTRA_TANK) == 1
            && getFrequency(SafetyCardType.PUNCTURE_PROOF) == 1
            && getFrequency(SafetyCardType.RIGHT_OF_WAY) == 1
            && getFrequency(DistanceCardType.MILES_25) == 10
            && getFrequency(DistanceCardType.MILES_50) == 10
            && getFrequency(DistanceCardType.MILES_75) == 10
            && getFrequency(DistanceCardType.MILES_100) == 12
            && getFrequency(DistanceCardType.MILES_200) == 4) {
            System.out.println("There are the correct number of specific cards in the deck");
            /*for (Map.Entry<CardType, Integer> entry : correctCardCounts.entrySet()) {
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }*/
        } else {
            System.err.println("There are not the correct number of specific cards in the deck");
        }
    }

    @Ignore
    public void testDisplay() {
        Deck instance = new Deck();
        instance.display();
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

        instance.getDeck().stream().map((card) -> card.getType()).forEachOrdered((type) -> {
            Integer count = frequency.get(type);

            if (count == null) {
                count = 0;
            }
            count++;
            frequency.put(type, count);
        });
    }

}
