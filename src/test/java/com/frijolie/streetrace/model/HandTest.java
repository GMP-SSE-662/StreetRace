package com.frijolie.streetrace.model;

import com.frijolie.streetrace.model.Hand;
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
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class HandTest {

    Hand instance;

    @Before
    public void setUp() {
        instance = new Hand();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testHand_AddValidCard() {
        DistanceCard miles200 = new DistanceCard(DistanceCardType.MILES_200);
        boolean result = instance.addCard(miles200);
        assertTrue(result);
    }

    @Test
    public void testHand_AddMiles25Card() {
        Card miles25 = new DistanceCard(DistanceCardType.MILES_25);
        assertTrue(instance.addCard(miles25));
    }

    @Test
    public void testHand_AddMiles50Card() {
        Card miles50 = new DistanceCard(DistanceCardType.MILES_50);
        assertTrue(instance.addCard(miles50));
    }

    @Test
    public void testHand_AddMiles75Card() {
        Card miles75 = new DistanceCard(DistanceCardType.MILES_75);
        assertTrue(instance.addCard(miles75));
    }

   @Test
    public void testHand_AddMiles100Card() {
        Card miles100 = new DistanceCard(DistanceCardType.MILES_100);
        assertTrue(instance.addCard(miles100));
    }

   @Test
    public void testHand_AddMiles200Card() {
        Card miles200 = new DistanceCard(DistanceCardType.MILES_200);
        assertTrue(instance.addCard(miles200));
    }

    @Test
    public void testHand_AddSpeedLimitCard() {
        Card speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        assertTrue(instance.addCard(speedLimit));
    }

    @Test
    public void testHand_AddAccidentCard() {
        Card accident = new HazardCard(HazardCardType.ACCIDENT);
        assertTrue(instance.addCard(accident));
    }

    @Test
    public void testHand_AddOutOfGasCard() {
        Card outOfGas = new HazardCard(HazardCardType.OUT_OF_GAS);
        assertTrue(instance.addCard(outOfGas));
    }

    @Test
    public void testHand_AddFlatTireCard() {
        Card flatTire = new HazardCard(HazardCardType.FLAT_TIRE);
        assertTrue(instance.addCard(flatTire));
    }

    @Test
    public void testHand_AddStopCard() {
        Card stop = new HazardCard(HazardCardType.STOP);
        assertTrue(instance.addCard(stop));
    }

    @Test
    public void testHand_AddEndLimitCard() {
        Card endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        assertTrue(instance.addCard(endLimit));
    }

    @Test
    public void testHand_AddRepairCard() {
        Card repair = new RemedyCard(RemedyCardType.REPAIR);
        assertTrue(instance.addCard(repair));
    }

    @Test
    public void testHand_AddGasolineCard() {
        Card gasoline = new RemedyCard(RemedyCardType.GASOLINE);
        assertTrue(instance.addCard(gasoline));
    }

    @Test
    public void testHand_AddSpareTireCard() {
        Card spareTire = new RemedyCard(RemedyCardType.SPARE_TIRE);
        assertTrue(instance.addCard(spareTire));
    }

    @Test
    public void testHand_AddRollCard() {
        Card roll = new RemedyCard(RemedyCardType.ROLL);
        assertTrue(instance.addCard(roll));
    }

    @Test
    public void testHand_AddDrivingAceCard() {
        Card drivingAce = new SafetyCard(SafetyCardType.DRIVING_ACE);
        assertTrue(instance.addCard(drivingAce));
    }

    @Test
    public void testHand_AddExtraTankCard() {
        Card extraTank = new SafetyCard(SafetyCardType.EXTRA_TANK);
        assertTrue(instance.addCard(extraTank));
    }

    @Test
    public void testHand_AddPunctureProofCard() {
        Card punctureProof = new SafetyCard(SafetyCardType.PUNCTURE_PROOF);
        assertTrue(instance.addCard(punctureProof));
    }

    @Test
    public void testHand_AddRightOfWayCard() {
        Card rightOfWay = new SafetyCard(SafetyCardType.RIGHT_OF_WAY);
        assertTrue(instance.addCard(rightOfWay));
    }


    @Test
    public void testHand_AddNullCard() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("You cannot add a null card to the hand");
        instance.addCard(null);
    }

    @Test
    public void testHand_RemoveValidCard() {
        DistanceCard miles100 = new DistanceCard(DistanceCardType.MILES_100);
        instance.addCard(miles100);
        assertTrue(instance.removeCard(miles100));
    }

    @Test
    public void testHand_RemoveInvalidCard() {
        DistanceCard miles100 = new DistanceCard(DistanceCardType.MILES_100);
        SafetyCard extraTank = new SafetyCard(SafetyCardType.EXTRA_TANK);
        instance.addCard(miles100);
        assertFalse(instance.removeCard(extraTank));
    }

    @Test
    public void testHand_RemoveNullCard() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("You cannot remove a null card from the hand");
        instance.removeCard(null);
    }

}