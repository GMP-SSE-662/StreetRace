import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import com.frijolie.streetrace.model.cards.Card;
import com.frijolie.streetrace.model.cards.DistanceCard;
import com.frijolie.streetrace.model.cards.DistanceCardType;
import com.frijolie.streetrace.model.Hand;
import com.frijolie.streetrace.model.cards.HazardCard;
import com.frijolie.streetrace.model.cards.HazardCardType;
import com.frijolie.streetrace.model.cards.RemedyCard;
import com.frijolie.streetrace.model.cards.RemedyCardType;
import com.frijolie.streetrace.model.cards.SafetyCard;
import com.frijolie.streetrace.model.cards.SafetyCardType;
import com.frijolie.streetrace.model.cards.SpeedCard;
import com.frijolie.streetrace.model.cards.SpeedCardType;

public class HandTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testAddValidCardToHandShouldBeTrue() {
        DistanceCard miles200 = new DistanceCard(DistanceCardType.MILES_200);
        Hand instance = new Hand();
        boolean result = instance.addCard(miles200);
        assertTrue(result);
        if (result) {
            System.out.println("You successfully added a valid card to the hand.");
        } else {
            System.err.println("The hand did not accept a valid card");
        }
    }

    @Test
    public void testAddAllValidCardsToHand() {
        Hand instance = new Hand();
        Card miles25 = new DistanceCard(DistanceCardType.MILES_25);
        Card miles50 = new DistanceCard(DistanceCardType.MILES_50);
        Card miles75 = new DistanceCard(DistanceCardType.MILES_75);
        Card miles100 = new DistanceCard(DistanceCardType.MILES_100);
        Card miles200 = new DistanceCard(DistanceCardType.MILES_200);
        Card speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        Card accident = new HazardCard(HazardCardType.ACCIDENT);
        Card outOfGas = new HazardCard(HazardCardType.OUT_OF_GAS);
        Card flatTire = new HazardCard(HazardCardType.FLAT_TIRE);
        Card stop = new HazardCard(HazardCardType.STOP);
        Card endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        Card repair = new RemedyCard(RemedyCardType.REPAIR);
        Card gasoline = new RemedyCard(RemedyCardType.GASOLINE);
        Card spareTire = new RemedyCard(RemedyCardType.SPARE_TIRE);
        Card roll = new RemedyCard(RemedyCardType.ROLL);
        Card drivingAce = new SafetyCard(SafetyCardType.DRIVING_ACE);
        Card extraTank = new SafetyCard(SafetyCardType.EXTRA_TANK);
        Card punctureProof = new SafetyCard(SafetyCardType.PUNCTURE_PROOF);
        Card rightOfWay = new SafetyCard(SafetyCardType.RIGHT_OF_WAY);
        assertTrue(instance.addCard(miles25));
        assertTrue(instance.addCard(miles50));
        assertTrue(instance.addCard(miles75));
        assertTrue(instance.addCard(miles100));
        assertTrue(instance.addCard(miles200));
        assertTrue(instance.addCard(speedLimit));
        assertTrue(instance.addCard(accident));
        assertTrue(instance.addCard(outOfGas));
        assertTrue(instance.addCard(flatTire));
        assertTrue(instance.addCard(stop));
        assertTrue(instance.addCard(endLimit));
        assertTrue(instance.addCard(repair));
        assertTrue(instance.addCard(gasoline));
        assertTrue(instance.addCard(spareTire));
        assertTrue(instance.addCard(roll));
        assertTrue(instance.addCard(drivingAce));
        assertTrue(instance.addCard(extraTank));
        assertTrue(instance.addCard(punctureProof));
        assertTrue(instance.addCard(rightOfWay));

        if (instance.addCard(miles25)
            && instance.addCard(miles50)
            && instance.addCard(miles75)
            && instance.addCard(miles100)
            && instance.addCard(miles200)
            && instance.addCard(speedLimit)
            && instance.addCard(accident)
            && instance.addCard(outOfGas)
            && instance.addCard(flatTire)
            && instance.addCard(stop)
            && instance.addCard(endLimit)
            && instance.addCard(repair)
            && instance.addCard(gasoline)
            && instance.addCard(spareTire)
            && instance.addCard(roll)
            && instance.addCard(drivingAce)
            && instance.addCard(extraTank)
            && instance.addCard(punctureProof)
            && instance.addCard(rightOfWay)) {
            System.out.println("All cards were accepted in the hand");
        } else {
            System.err.println("All cards were not accpeted in the hand");
        }
    }

    @Test
    public void testAddNullCardShouldReceiveMessage() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("You cannot add a null card to the hand");
        Hand instance = new Hand();
        instance.addCard(null);
    }

    @Test
    public void testRemoveValidCardShouldBeTrue() {
        DistanceCard miles100 = new DistanceCard(DistanceCardType.MILES_100);
        SafetyCard extraTank = new SafetyCard(SafetyCardType.EXTRA_TANK);
        Hand instance = new Hand();
        instance.addCard(miles100);
        instance.addCard(extraTank);
        boolean result = instance.removeCard(miles100);
        assertTrue(result);
        if (result) {
            System.out.println("The valid card was successfully removed from the hand");
        } else {
            System.err.println("The valid card was not removed from the hand");
        }
    }

    @Test
    public void testRemoveInvalidCardShouldBeFalse() {
        DistanceCard miles100 = new DistanceCard(DistanceCardType.MILES_100);
        SafetyCard extraTank = new SafetyCard(SafetyCardType.EXTRA_TANK);
        Hand instance = new Hand();
        instance.addCard(miles100);
        boolean result = instance.removeCard(extraTank);
        assertFalse(result);
        if (result) {
            System.err.println("An invalid card was removed from the hand?! O.o?");
        } else {
            System.out.println("An invalid card was not removed from the hand, was expected");
        }
    }

    @Test
    public void testRemoveNullCardShouldReceiveMessage() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("You cannot remove a null card from the hand");
        Hand instance = new Hand();
        instance.removeCard(null);
    }

    @Ignore
    public void testGetHand() {
        Hand instance = new Hand();
        List<Card> expResult = null;
        List<Card> result = instance.getHand();
        assertEquals(expResult, result);
    }

    @Test
    public void testDisplayHand() {
        Hand instance = new Hand();
        DistanceCard miles100 = new DistanceCard(DistanceCardType.MILES_100);
        SafetyCard extraTank = new SafetyCard(SafetyCardType.EXTRA_TANK);
        DistanceCard miles25 = new DistanceCard(DistanceCardType.MILES_25);
        DistanceCard twenty5Miles = new DistanceCard(DistanceCardType.MILES_25);
        SafetyCard pProof = new SafetyCard(SafetyCardType.PUNCTURE_PROOF);
        instance.addCard(miles100);
        instance.addCard(extraTank);
        instance.addCard(miles25);
        instance.addCard(twenty5Miles);
        instance.addCard(pProof);
        instance.displayHand();
    }

}
