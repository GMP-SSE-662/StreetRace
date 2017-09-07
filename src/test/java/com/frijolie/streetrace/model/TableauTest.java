package com.frijolie.streetrace.model;

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
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TableauTest {

    Tableau instance;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        instance = new Tableau();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testTableau_CalculateTotalMiles() {
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
    public void testTableau_AddVailidDistanceCardToDistancePile() {
        DistanceCard card = new DistanceCard(DistanceCardType.MILES_200);
        assertTrue(instance.addToDistancePile(card));
    }

    @Test
    public void testTableau_AddAllValidDistanceCardsToDistancePile() {

        DistanceCard miles25 = new DistanceCard(DistanceCardType.MILES_25);
        DistanceCard miles50 = new DistanceCard(DistanceCardType.MILES_50);
        DistanceCard miles75 = new DistanceCard(DistanceCardType.MILES_75);
        DistanceCard miles100 = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles200 = new DistanceCard(DistanceCardType.MILES_200);

        instance.addToDistancePile(miles200);
        instance.addToDistancePile(miles100);
        instance.addToDistancePile(miles75);
        instance.addToDistancePile(miles50);
        instance.addToDistancePile(miles25);

        assertEquals(instance.getDistancePile().size(),5);
    }

    @Test
    public void testTableau_AddNullCardToDistancePile() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("You can\'t add a null value card to the Distance Pile");
        instance.addToDistancePile(null);
    }

    @Test
    public void testTableau_AddThreeMiles200CardsToDistancePile() {
        DistanceCard miles200A = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard miles200B = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard miles200C = new DistanceCard(DistanceCardType.MILES_200);
        instance.addToDistancePile(miles200A);
        instance.addToDistancePile(miles200B);
        assertFalse(instance.addToDistancePile(miles200C));
    }

    @Test
    public void testTableau_AddValidSafetyCardToSafetyPile() {
        SafetyCard card = new SafetyCard(SafetyCardType.EXTRA_TANK);
        assertTrue(instance.addToSafetyPile(card));
    }

    @Test
    public void testTableau_AddNullCardToSafetyPile() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("You can\'t add a null value card to the Safety Pile");
        instance.addToSafetyPile(null);
    }

    @Test
    public void testTableau_AddAllValidSafetyCardsToSafetyPile() {
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
    public void testTableau_AddValidSpeedCardToSpeedPile() {
        SpeedCard card = new SpeedCard(SpeedCardType.END_LIMIT);
        instance.addToSpeedPile(card);
        int result = instance.getSpeedPile().search(card);
        assertEquals(result,1);
    }

    @Test
    public void testTableau_AddAllValidSpeedCardsToSpeedPile() {
        SpeedCard card = new SpeedCard(SpeedCardType.END_LIMIT);
        SpeedCard card1 = new SpeedCard(SpeedCardType.SPEED_LIMIT);

        instance.addToSpeedPile(card);
        instance.addToSpeedPile(card1);

        assertEquals(instance.getSpeedPile().size(), 2);
    }

    @Test
    public void testTableau_AddNullCardToSpeedPile() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("You can\'t add a null value card to the Speed Pile");
        instance.addToSpeedPile(null);
    }

    @Test
    public void testTableau_AddValidBattleCardToBattlePile() {
        BattleCard card = new HazardCard(HazardCardType.ACCIDENT);
        instance.addToBattlePile(card);
        int result = instance.getBattlePile().search(card);
        assertEquals(result,1);
    }

    @Test
    public void testTableau_AddNullCardToBattlePile() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("You can\'t add a null value card to the Battle Pile");
        instance.addToBattlePile(null);
    }

    @Test
    public void testTableau_AddAllValidBattleCardsToBattlePile() {
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
    public void testTableau_IsRollingBattlePileIsEmpty() {
        assertFalse(instance.isRolling());
    }

    @Test
    public void testTableau_IsRollingBattlePileNotEmptyTopCardNotRoll() {
        BattleCard accident = new HazardCard(HazardCardType.ACCIDENT);
        Stack<BattleCard> battlePile = instance.getBattlePile();
        battlePile.add(accident);
        assertFalse(instance.isRolling());
    }

    @Test
    public void testTableau_IsRollingBattlePileNotEmptyTopCardIsRoll() {
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        instance.addToBattlePile(roll);
        assertTrue(instance.isRolling());
    }

    @Test
    public void testTableau_HasHazardBattlePileIsEmpty() {
        assertFalse(instance.hasHazard());
    }

    @Test
    public void testTableau_HasHazardBattlePileNotEmptyTopCardIsHazard() {
        HazardCard stop = new HazardCard(HazardCardType.STOP);
        instance.addToBattlePile(stop);
        Card card = instance.getBattlePileTopCard();
        assertTrue(instance.hasHazard());
    }

    @Test
    public void testTableau_HasHazardBattlePileNotEmptyTopCardIsNotHazard() {
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        instance.addToBattlePile(roll);
        Card card = instance.getBattlePileTopCard();
        assertFalse(instance.hasHazard());
    }

    @Test
    public void testTableau_HasSpeedLimitSpeedPileIsEmpty() {
        assertFalse(instance.hasSpeedLimit());
    }

    @Test
    public void testTableau_HasSpeedLimitSpeedPileNotEmptyTopCardIsSpeedLimit() {
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        instance.addToSpeedPile(speedLimit);
        assertTrue(instance.hasSpeedLimit());
    }

    @Test
    public void testTableau_HasSpeedLimitSpeedPileNotEmptyTopCardIsNotSpeedLimit() {
        SpeedCard endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        instance.addToSpeedPile(endLimit);
        assertFalse(instance.hasSpeedLimit());
    }

}
