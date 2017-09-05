import com.frijolie.streetrace.model.Tableau;
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
import java.util.Stack;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    }

    @Test
    public void testAddAllValidDistanceCardsToDistancePile() {
        Tableau instance = new Tableau();

        DistanceCard card4 = new DistanceCard(DistanceCardType.MILES_25);
        DistanceCard card3 = new DistanceCard(DistanceCardType.MILES_50);
        DistanceCard card2 = new DistanceCard(DistanceCardType.MILES_75);
        DistanceCard card1 = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard card = new DistanceCard(DistanceCardType.MILES_200);

        instance.addToDistancePile(card);
        instance.addToDistancePile(card1);
        instance.addToDistancePile(card2);
        instance.addToDistancePile(card3);
        instance.addToDistancePile(card4);

        assertEquals(instance.getDistancePile().size(),5);
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

        instance.addToSafetyPile(drivingAce);
        instance.addToSafetyPile(extraTank);
        instance.addToSafetyPile(punctureProof);
        instance.addToSafetyPile(rightOfWay);

        assertEquals(instance.getSafetyPile().size(),4);

    }

    @Test
    public void testAddValidSpeedCardToSpeedPile() {
        SpeedCard card = new SpeedCard(SpeedCardType.END_LIMIT);
        Tableau instance = new Tableau();
        instance.addToSpeedPile(card);
        assertEquals(instance.getSpeedPile().size(), 1);
    }

    @Test
    public void testAddAllValidSpeedCardsToSpeedPile() {
        Tableau instance = new Tableau();
        SpeedCard card = new SpeedCard(SpeedCardType.END_LIMIT);
        SpeedCard card1 = new SpeedCard(SpeedCardType.SPEED_LIMIT);

        instance.addToSpeedPile(card);
        instance.addToSpeedPile(card1);
        
        assertEquals(instance.getSpeedPile().size(), 2);
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
        instance.addToBattlePile(card);
        assertEquals(instance.getBattlePile().size(),1);
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

        instance.addToBattlePile(accident);
        instance.addToBattlePile(outOfGas);
        instance.addToBattlePile(flatTire);
        instance.addToBattlePile(stop);
        instance.addToBattlePile(repair);
        instance.addToBattlePile(gasoline);
        instance.addToBattlePile(spareTire);
        instance.addToBattlePile(roll);

        assertEquals(instance.getBattlePile().size(),8);
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
        Stack<BattleCard> battlePile = instance.getBattlePile();
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
