package com.frijolie.streetrace.model;

import com.frijolie.streetrace.model.cards.BattleCard;
import com.frijolie.streetrace.model.cards.Card;
import com.frijolie.streetrace.model.cards.DistanceCard;
import com.frijolie.streetrace.model.cards.DistanceCardType;
import com.frijolie.streetrace.model.cards.HazardCard;
import com.frijolie.streetrace.model.cards.HazardCardType;
import com.frijolie.streetrace.model.cards.SafetyCard;
import com.frijolie.streetrace.model.cards.SafetyCardType;
import com.frijolie.streetrace.model.cards.SpeedCard;
import com.frijolie.streetrace.model.cards.SpeedCardType;
import java.util.List;
import java.util.Stack;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class StreetRaceGameTest {

    StreetRaceGame game;
    Player player;
    Player computer;
    List<Card> playerHand;
    List<Card> computerHand;
    Tableau playerTableau;
    Tableau computerTableau;
    Deck deck;
    Stack<Card> drawPile;
    Stack<Card> discardPile;

    @Before
    public void setUp() {
        game = StreetRaceGame.getInstance();
        player = game.getPlayer();
        computer = game.getComputer();
        playerHand = game.getPlayerHand().getList();
        computerHand = game.getComputerHand().getList();
        playerTableau = game.getPlayerTableau();
        computerTableau = game.getComputerTableau();
        deck = game.getDeck();
        drawPile = game.getDrawPile();
        discardPile = game.getDiscardPile();
        game.startGame();
    }

    @After
    public void tearDown() {
        game.reset();
        player = null;
        computer = null;
        playerHand = null;
        computerHand = null;
        playerTableau = null;
        computerTableau = null;
        deck = null;
        drawPile = null;
        discardPile = null;
    }

    @Test
    public void testStreetRaceGame_GetGameInstance() {
        assertNotNull(game);
    }

    @Test
    public void testStreetRaceGame_PlayerHandNotNull() {
        assertNotNull(playerHand);
    }

    @Test
    public void testStreetRaceGame_ComputerHandNotNull() {
        assertNotNull(computerHand);
    }

    @Test
    public void testStreetRaceGame_DeckNotNull() {
        assertNotNull(deck);
    }

    @Test
    public void testStreetRaceGame_PlayerTableauNotNull() {
        assertNotNull(playerTableau);
    }

    @Test
    public void testStreetRaceGame_ComputerTableauNotNull() {
        assertNotNull(computerTableau);
    }

    @Test
    public void testStreetRaceGame_DiscardPileNotNull() {
        assertNotNull(discardPile);
    }

    @Test
    public void testStreetRaceGame_DrawPileNotNull() {
        assertNotNull(drawPile);
    }

    @Test
    public void testStreetRaceGame_DrawPileContains106Cards() {
        drawPile.clear();
        drawPile.addAll(deck.getList());
        assertEquals(drawPile.size(), 106);
    }

    @Test
    public void testStreetRaceGame_PlayerOpponentTableauNotNull() {
        assertNotNull(player.getOpponentTableau());
    }

    @Test
    public void testStreetRaceGame_ComputerOpponentTableauNotNull() {
        assertNotNull(computer.getOpponentTableau());
    }

    @Test
    public void testStreetRaceGame_PlayerOpponentTableauEqualsComputerTableau() {
        assertEquals(player.getOpponentTableau(), computerTableau);
    }

    @Test
    public void testStreetRaceGame_ComputerOpponentTableauEqualsPlayerTableau() {
        assertEquals(computer.getOpponentTableau(), playerTableau);
    }

    @Test
    public void testStreetRaceGame_DeckNotEmpty() {
        assertFalse(deck.getList().isEmpty());
    }

    @Test
    public void testStreetRaceGame_PlayerHandNotEmpty() {
        assertFalse(playerHand.isEmpty());
    }

    @Test
    public void testStreetRaceGame_ComputerHandNotEmpty() {
        assertFalse(computerHand.isEmpty());
    }

    @Test
    public void testStreetRaceGame_PlayerHandContainsSevenCardsAfterDeal() {
        assertEquals(playerHand.size(), 7);
    }

    @Test
    public void testStreetRaceGame_ComputerHandContainsSevenCardsAfterDeal() {
        assertEquals(computerHand.size(), 7);
    }

    @Test
    public void testStreetRaceGame_IsDrawPileEmpty() {
        drawPile.clear();
        assertTrue(game.isDrawPileEmtpy());
    }

    @Test
    public void testStreetRaceGame_PlayerSpeedPileTopCardTrue() {
        playerTableau.getSpeedPile().clear();
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        playerTableau.addToSpeedPile(speedLimit);
        assertEquals(game.speedPileTopCard(player),speedLimit);
    }

    @Test
    public void testStreetRaceGame_ComputerSpeedPileTopCardTrue() {
        computerTableau.getSpeedPile().clear();
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        computerTableau.addToSpeedPile(speedLimit);
        assertEquals(game.speedPileTopCard(computer),speedLimit);
    }

    @Test
    public void testStreetRaceGame_PlayerSpeedPileTopCardFalse() {
        playerTableau.getSpeedPile().clear();
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        SpeedCard endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        playerTableau.addToSpeedPile(speedLimit);
        playerTableau.addToSpeedPile(endLimit);
        assertFalse(game.speedPileTopCard(player) == speedLimit);
    }

    @Test
    public void testStreetRaceGame_ComputerSpeedPileTopCardFalse() {
        computerTableau.getSpeedPile().clear();
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        SpeedCard endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        computerTableau.addToSpeedPile(speedLimit);
        computerTableau.addToSpeedPile(endLimit);
        assertFalse(game.speedPileTopCard(computer) == speedLimit);
    }

    @Test
    public void testStreetRaceGame_PlayerBattlePileTopCardTrue() {
        playerTableau.getBattlePile().clear();
        BattleCard stop = new HazardCard(HazardCardType.STOP);
        playerTableau.addToBattlePile(stop);
        assertEquals(playerTableau.getBattlePileTopCard(),stop);
    }

    @Test
    public void testStreetRaceGame_ComputerBattlePileTopCardTrue() {
        computerTableau.getBattlePile().clear();
        BattleCard stop = new HazardCard(HazardCardType.STOP);
        computerTableau.addToBattlePile(stop);
        assertEquals(computerTableau.getBattlePileTopCard(),stop);
    }

    @Test
    public void testStreetRaceGame_PlayerBattlePileTopCardFalse() {
        playerTableau.getBattlePile().clear();
        BattleCard stop = new HazardCard(HazardCardType.STOP);
        BattleCard outOfGas = new HazardCard(HazardCardType.OUT_OF_GAS);
        playerTableau.addToBattlePile(stop);
        assertFalse(game.battlePileTopCard(player) == outOfGas);
    }

    @Test
    public void testStreetRaceGame_ComputerBattlePileTopCardFalse() {
        computerTableau.getBattlePile().clear();
        BattleCard stop = new HazardCard(HazardCardType.STOP);
        BattleCard outOfGas = new HazardCard(HazardCardType.OUT_OF_GAS);
        computerTableau.addToBattlePile(stop);
        assertFalse(game.battlePileTopCard(computer) == outOfGas);
    }

    @Test
    public void testStreetRaceGame_CardLocationPlayerHand() {
        playerHand.clear();
        BattleCard stop = new HazardCard(HazardCardType.STOP);
        game.getPlayer().getHand().addCard(stop);
        assertTrue(game.getLocation(stop) == CardLocation.PLAYER_HAND);
    }

    @Test
    public void testStreetRaceGame_CardLocationPlayerDistancePile() {
        playerTableau.getDistancePile().clear();
        DistanceCard miles200 = new DistanceCard(DistanceCardType.MILES_200);
        playerTableau.addToDistancePile(miles200);
        assertTrue(game.getLocation(miles200) == CardLocation.PLAYER_DISTANCE_PILE);
    }

    @Test
    public void testStreetRaceGame_CardLocationPlayerSpeedPile() {
        playerTableau.getDistancePile().clear();
        SpeedCard endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        playerTableau.addToSpeedPile(endLimit);
        assertTrue(game.getLocation(endLimit) == CardLocation.PLAYER_SPEED_PILE);
    }

    @Test
    public void testStreetRaceGame_CardLocationPlayerSafetyPile() {
        playerTableau.getSafetyPile().clear();
        SafetyCard extraTank = new SafetyCard(SafetyCardType.EXTRA_TANK);
        playerTableau.addToSafetyPile(extraTank);
        assertTrue(game.getLocation(extraTank) == CardLocation.PLAYER_SAFETY_PILE);
    }

    @Test
    public void testStreetRaceGame_CardLocationPlayerBattlePile() {
        playerTableau.getBattlePile().clear();
        BattleCard outOfGas = new HazardCard(HazardCardType.OUT_OF_GAS);
        playerTableau.addToBattlePile(outOfGas);
        assertTrue(game.getLocation(outOfGas) == CardLocation.PLAYER_BATTLE_PILE);
    }

    @Test
    public void testStreetRaceGame_CardLocationComputerHand() {
        computerHand.clear();
        SafetyCard extraTank = new SafetyCard(SafetyCardType.EXTRA_TANK);
        computerHand.add(extraTank);
        assertTrue(game.getLocation(extraTank) == CardLocation.COMPUTER_HAND);
    }

    @Test
    public void testStreetRaceGame_CardLocationComputerDistancePile() {
        computerTableau.getDistancePile().clear();
        DistanceCard miles25 = new DistanceCard(DistanceCardType.MILES_25);
        computerTableau.addToDistancePile(miles25);
        assertTrue(game.getLocation(miles25) == CardLocation.COMPUTER_DISTANCE_PILE);
    }

    @Test
    public void testStreetRaceGame_CardLocationComputerSpeedPile() {
        computerTableau.getDistancePile().clear();
        SpeedCard endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        computerTableau.addToSpeedPile(endLimit);
        assertTrue(game.getLocation(endLimit) == CardLocation.COMPUTER_SPEED_PILE);
    }

    @Test
    public void testStreetRaceGame_CardLocationComputerSafetyPile() {
        computerTableau.getSafetyPile().clear();
        SafetyCard extraTank = new SafetyCard(SafetyCardType.EXTRA_TANK);
        computerTableau.addToSafetyPile(extraTank);
        assertTrue(game.getLocation(extraTank) == CardLocation.COMPUTER_SAFETY_PILE);
    }

    @Test
    public void testStreetRaceGame_CardLocationComputerBattlePile() {
        computerTableau.getBattlePile().clear();
        BattleCard outOfGas = new HazardCard(HazardCardType.OUT_OF_GAS);
        computerTableau.addToBattlePile(outOfGas);
        assertTrue(game.getLocation(outOfGas) == CardLocation.COMPUTER_BATTLE_PILE);
    }

    @Test
    public void testeStreetRaceGame_CardLocationDrawPile() {
        drawPile.clear();
        BattleCard outOfGas = new HazardCard(HazardCardType.OUT_OF_GAS);
        SafetyCard extraTank = new SafetyCard(SafetyCardType.EXTRA_TANK);
        SpeedCard endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        drawPile.push(outOfGas);
        drawPile.push(extraTank);
        drawPile.push(endLimit);
        assertTrue(game.getLocation(outOfGas) == CardLocation.DRAW_PILE);
    }

    @Test
    public void testStreetRaceGame_CardLocationDiscardPile() {
        discardPile.clear();
        BattleCard stop = new HazardCard(HazardCardType.STOP);
        game.addToDiscardPile(stop);
        assertTrue(game.getLocation(stop) == CardLocation.DISCARD_PILE);
    }

}