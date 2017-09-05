import java.util.Deque;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import com.frijolie.streetrace.model.BattleCard;
import com.frijolie.streetrace.model.Card;
import com.frijolie.streetrace.model.DistanceCard;
import com.frijolie.streetrace.model.DistanceCardType;
import com.frijolie.streetrace.model.HazardCard;
import com.frijolie.streetrace.model.HazardCardType;
import com.frijolie.streetrace.model.RemedyCard;
import com.frijolie.streetrace.model.RemedyCardType;
import com.frijolie.streetrace.model.SafetyCard;
import com.frijolie.streetrace.model.SafetyCardType;
import com.frijolie.streetrace.model.SpeedCard;
import com.frijolie.streetrace.model.SpeedCardType;
import com.frijolie.streetrace.model.Tableau;

public class TableauTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testCalculateTotalMiles() {
        Tableau instance = new Tableau();
        int expResult = 625;
        DistanceCard card = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard card1 = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard card2 = new DistanceCard(DistanceCardType.MILES_25);
        DistanceCard card3 = new DistanceCard(DistanceCardType.MILES_75);
        DistanceCard card4 = new DistanceCard(DistanceCardType.MILES_50);
        DistanceCard card5 = new DistanceCard(DistanceCardType.MILES_75);
        DistanceCard card6 = new DistanceCard(DistanceCardType.MILES_200);
        instance.addToDistancePile(card);
        instance.addToDistancePile(card1);
        instance.addToDistancePile(card2);
        instance.addToDistancePile(card3);
        instance.addToDistancePile(card4);
        instance.addToDistancePile(card5);
        instance.addToDistancePile(card6);
        int result = instance.calculateTotalMiles();
        assertEquals(expResult, result);
    }

    @Test
    public void testAddVailidDistanceCardToDistancePile() {
        DistanceCard card = new DistanceCard(DistanceCardType.MILES_200);
        Tableau instance = new Tableau();
        assertTrue(instance.addToDistancePile(card));
        if (instance.addToDistancePile(card)) {
            System.out.println("A valid DistanceCard was accepted in the Distance Pile");
        } else {
            System.err.println("A valid DistanceCard was NOT accepted in the Distance Pile");
        }
    }

    @Test
    public void testAddAllValidDistanceCardsToDistancePile() {
        Tableau instance = new Tableau();

        DistanceCard card4 = new DistanceCard(DistanceCardType.MILES_25);
        DistanceCard card3 = new DistanceCard(DistanceCardType.MILES_50);
        DistanceCard card2 = new DistanceCard(DistanceCardType.MILES_75);
        DistanceCard card1 = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard card = new DistanceCard(DistanceCardType.MILES_200);

        assertTrue(instance.addToDistancePile(card));
        assertTrue(instance.addToDistancePile(card1));
        assertTrue(instance.addToDistancePile(card2));
        assertTrue(instance.addToDistancePile(card3));
        assertTrue(instance.addToDistancePile(card4));

        if (instance.addToDistancePile(card)
            && instance.addToDistancePile(card1)
            && instance.addToDistancePile(card2)
            && instance.addToDistancePile(card3)
            && instance.addToDistancePile(card1)) {
            System.out.println("All valid DistanceCards were accepted in the Distance Pile");
        } else {
            System.err.println("All valid DistanceCards were NOT accepted into the Distance Pile");
        }
    }

    @Test
    public void testAddNullCardToDistancePileShouldReceiveMessage() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("You can\'t add a null value card to the Distance Pile");
        Tableau instance = new Tableau();
        instance.addToDistancePile(null);
    }

    @Test
    public void testAddThreeMiles200CardsToDistancePileShouldBeFalse() {
        Tableau instance = new Tableau();
        DistanceCard card1 = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard card2 = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard card3 = new DistanceCard(DistanceCardType.MILES_200);
        instance.addToDistancePile(card1);
        instance.addToDistancePile(card2);
        boolean result = instance.addToDistancePile(card3);
        assertFalse(result);
    }

    @Test
    public void testAddValidSafetyCardToSafetyPile() {
        SafetyCard card = new SafetyCard(SafetyCardType.EXTRA_TANK);
        Tableau instance = new Tableau();

        assertTrue(instance.addToSafetyPile(card));

        if (instance.addToSafetyPile(card)) {
            System.out.println("A valid SafetyCard was accepted in the Safety Pile");
        } else {
            System.err.println("A valid SafetyCard was NOT accepted in the Safety Pile");
        }
    }

    @Test
    public void testAddNullCardToSafetyPileShouldReceiveMessage() {
        Tableau instance = new Tableau();
        exception.expect(NullPointerException.class);
        exception.expectMessage("You can\'t add a null value card to the Safety Pile");
        instance.addToSafetyPile(null);
    }

    @Test
    public void testAddAllValidSafetyCardsToSafetyPile() {
        Tableau instance = new Tableau();

        SafetyCard drivingAce = new SafetyCard(SafetyCardType.DRIVING_ACE);
        SafetyCard extraTank = new SafetyCard(SafetyCardType.EXTRA_TANK);
        SafetyCard punctureProof = new SafetyCard(SafetyCardType.PUNCTURE_PROOF);
        SafetyCard rightOfWay = new SafetyCard(SafetyCardType.RIGHT_OF_WAY);

        assertTrue(instance.addToSafetyPile(drivingAce));
        assertTrue(instance.addToSafetyPile(extraTank));
        assertTrue(instance.addToSafetyPile(punctureProof));
        assertTrue(instance.addToSafetyPile(rightOfWay));

        if (instance.addToSafetyPile(drivingAce)
            && instance.addToSafetyPile(extraTank)
            && instance.addToSafetyPile(punctureProof)
            && instance.addToSafetyPile(rightOfWay)) {
            System.out.println("All valid SafetyCards were accepted in the Safety Pile");
        } else {
            System.err.println("All valid SafetyCards were not accepted in the Safety Pile");
        }

    }

    @Test
    public void testAddValidSpeedCardToSpeedPile() {
        SpeedCard card = new SpeedCard(SpeedCardType.END_LIMIT);
        Tableau instance = new Tableau();
        boolean result = instance.addToSpeedPile(card);
        assertTrue(result);
        if (result) {
            System.out.println("A valid SpeedCard was accepted in the Speed Pile");
        } else {
            System.err.println("A valid SpeedCard was NOT accepted in the Speed Pile");
        }
    }

    @Test
    public void testAddAllValidSpeedCardsToSpeedPile() {
        Tableau instance = new Tableau();
        SpeedCard card = new SpeedCard(SpeedCardType.END_LIMIT);
        SpeedCard card1 = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        assertTrue(instance.addToSpeedPile(card));
        assertTrue(instance.addToSpeedPile(card1));
        if (instance.addToSpeedPile(card)
            && instance.addToSpeedPile(card1)) {
            System.out.println("All valid SpeedCards were accepted in the Speed Pile");
        } else {
            System.err.println("All valid SpeedCards were NOT accepted in the Speed Pile");
        }
    }

    @Test
    public void testAddNullCardToSpeedPileShouldReceiveMessage() {
        Tableau instance = new Tableau();
        exception.expect(NullPointerException.class);
        exception.expectMessage("You can\'t add a null value card to the Speed Pile");
        instance.addToSpeedPile(null);
    }

    @Test
    public void testAddValidBattleCardToBattlePile() {
        BattleCard card = new HazardCard(HazardCardType.ACCIDENT);
        Tableau instance = new Tableau();
        boolean result = instance.addToBattlePile(card);
        assertTrue(result);
        if (result) {
            System.out.println("A valid BattleCard was accepted in the Battle Pile");
        } else {
            System.err.println("A valid BattleCard was NOT accepted in the Battle Pile");
        }
    }

    @Test
    public void testAddNullCardToBattlePileShouldReceiveMesage() {
        Tableau instance = new Tableau();
        exception.expect(NullPointerException.class);
        exception.expectMessage("You can\'t add a null value card to the Battle Pile");
        instance.addToBattlePile(null);
    }

    @Test
    public void testAddAllValidBattleCardsToBattlePile() {
        Tableau instance = new Tableau();

        HazardCard accident = new HazardCard(HazardCardType.ACCIDENT);
        HazardCard outOfGas = new HazardCard(HazardCardType.OUT_OF_GAS);
        HazardCard flatTire = new HazardCard(HazardCardType.FLAT_TIRE);
        HazardCard stop = new HazardCard(HazardCardType.STOP);
        BattleCard repair = new RemedyCard(RemedyCardType.REPAIR);
        BattleCard gasoline = new RemedyCard(RemedyCardType.GASOLINE);
        BattleCard spareTire = new RemedyCard(RemedyCardType.SPARE_TIRE);
        BattleCard roll = new RemedyCard(RemedyCardType.ROLL);

        assertTrue(instance.addToBattlePile(accident));
        assertTrue(instance.addToBattlePile(outOfGas));
        assertTrue(instance.addToBattlePile(flatTire));
        assertTrue(instance.addToBattlePile(stop));
        assertTrue(instance.addToBattlePile(repair));
        assertTrue(instance.addToBattlePile(gasoline));
        assertTrue(instance.addToBattlePile(spareTire));
        assertTrue(instance.addToBattlePile(roll));

        if (instance.addToBattlePile(accident)
            && instance.addToBattlePile(outOfGas)
            && instance.addToBattlePile(flatTire)
            && instance.addToBattlePile(stop)
            && instance.addToBattlePile(repair)
            && instance.addToBattlePile(gasoline)
            && instance.addToBattlePile(spareTire)
            && instance.addToBattlePile(roll)) {
            System.out.println("All valid BattleCards were accepted in the Battle Pile");
        } else {
            System.err.println("All valid BattleCards were NOT accepted in the Battle Pile");
        }
    }

    @Test
    public void testIsRollingBattlePileIsEmptyShouldBeFalse() {
        Tableau instance = new Tableau();
        assertFalse(instance.isRolling());
    }

    @Test
    public void testIsRollingBattlePileNotEmptyTopCardNotRollShouldBeFalse() {
        Tableau instance = new Tableau();
        BattleCard accident = new HazardCard(HazardCardType.ACCIDENT);
        Deque<BattleCard> battlePile = instance.getBattlePile();
        battlePile.add(accident);
        assertFalse(instance.isRolling());
    }

    @Test
    public void testIsRollingBattlePileNotEmptyTopCardIsRollShouldBeTrue() {
        Tableau instance = new Tableau();
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        instance.addToBattlePile(roll);
        assertTrue(instance.isRolling());
    }

    @Test
    public void testHasHazardBattlePileIsEmptyShouldBeFalse() {
        Tableau instance = new Tableau();
        assertFalse(instance.hasHazard());
    }

    @Test
    public void testHasHazardBattlePileNotEmptyTopCardIsHazardShouldBeTrue() {
        Tableau instance = new Tableau();
        HazardCard stop = new HazardCard(HazardCardType.STOP);
        instance.addToBattlePile(stop);
        Card card = instance.getBattlePileTopCard();
        assertTrue(instance.hasHazard());
    }

    @Test
    public void testHasHazardBattlePileNotEmptyTopCardIsNotHazardShouldBeFalse() {
        Tableau instance = new Tableau();
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        instance.addToBattlePile(roll);
        Card card = instance.getBattlePileTopCard();
        assertFalse(instance.hasHazard());
    }

    @Test
    public void testHasSpeedLimitBattlePileIsEmptyShouldBeFalse() {
        Tableau instance = new Tableau();
        assertFalse(instance.hasSpeedLimit());
    }

    @Test
    public void testHasSpeedLimitBattlePileNotEmptyTopCardIsSpeedLimitShouldBeTrue() {
        Tableau instance = new Tableau();
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        instance.addToSpeedPile(speedLimit);
        assertTrue(instance.hasSpeedLimit());
    }

    @Test
    public void testHasSpeedLimitBattlePileNotEmptyTopCardIsNotSpeedLimitShouldBeFalse() {
        Tableau instance = new Tableau();
        SpeedCard endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        instance.addToSpeedPile(endLimit);
        assertFalse(instance.hasSpeedLimit());
    }

}
