package com.frijolie.streetrace.model;

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
import javafx.collections.ObservableList;
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
    ObservableList<Card> playerHand;
    ObservableList<Card> computerHand;
    Tableau playerTableau;
    Tableau computerTableau;
    Deck deck;
    Stack<Card> drawPile;
    Stack<Card> discardPile;
    Stack<Card> playerSpeedPile;
    Stack<Card> computerSpeedPile;

    @Before
    public void setUp() {
        game = StreetRaceGame.getInstance();
        player = game.getPlayer();
        computer = game.getComputer();
        playerHand = game.getPlayerHand();
        computerHand = game.getComputerHand();
        playerTableau = game.getPlayerTableau();
        computerTableau = game.getComputerTableau();
        deck = game.getDeck();
        drawPile = game.getDrawPile();
        discardPile = game.getDiscardPile();
        playerSpeedPile = playerTableau.getSpeedPile();
        computerSpeedPile = computerTableau.getSpeedPile();
        game.startGame();
    }

    @After
    public void tearDown() {
        game.reset();
        player = null;
        computer = null;
        playerHand = null;
        computerHand = null;
        playerTableau.clear();
        computerTableau.clear();
        playerTableau = null;
        computerTableau = null;
        deck = null;
        drawPile = null;
        discardPile = null;
    }

    @Test
    public void testStreetRaceGame_GetGameInstance() {
        assertNotNull("game.getInstance() is null", game);
    }

    @Test
    public void testStreetRaceGame_PlayerHandNotNull() {
        assertNotNull("playerHand is null", playerHand);
    }

    @Test
    public void testStreetRaceGame_ComputerHandNotNull() {
        assertNotNull("computerHand is null", computerHand);
    }

    @Test
    public void testStreetRaceGame_DeckNotNull() {
        assertNotNull("deck is null", deck);
    }

    @Test
    public void testStreetRaceGame_PlayerTableauNotNull() {
        assertNotNull("playerTableau is null", playerTableau);
    }

    @Test
    public void testStreetRaceGame_ComputerTableauNotNull() {
        assertNotNull("computerTableau is null", computerTableau);
    }

    @Test
    public void testStreetRaceGame_DiscardPileNotNull() {
        assertNotNull("discardPile is null", discardPile);
    }

    @Test
    public void testStreetRaceGame_DrawPileNotNull() {
        assertNotNull("drawPile is null", drawPile);
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
        game.getPlayerHand().add(stop);
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

    @Test
    public void testStreetRaceGame_CalculateTotalMilesEquals975() {
        DistanceCard miles200 = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard miles200b = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard miles100 = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100b = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100c = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100d = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100e = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles75 = new DistanceCard(DistanceCardType.MILES_75);
        player.getTableau().addToDistancePile(miles200);
        player.getTableau().addToDistancePile(miles200b);
        player.getTableau().addToDistancePile(miles100);
        player.getTableau().addToDistancePile(miles100b);
        player.getTableau().addToDistancePile(miles100c);
        player.getTableau().addToDistancePile(miles100d);
        player.getTableau().addToDistancePile(miles100e);
        player.getTableau().addToDistancePile(miles75);
        assertEquals(player.getTableau().calculateTotalMiles(),975);
    }

    @Test
    public void testStreetRaceGame_CanMoveHereComputerDistanceCard() {
        DistanceCard miles25 = new DistanceCard(DistanceCardType.MILES_25);
        assertTrue(game.canMoveHere(computer,miles25,CardLocation.COMPUTER_DISTANCE_PILE) );
    }

    @Test
    public void testStreetRaceGame_CanMoveHereComputerSpeedCardEndLimit() {
        SpeedCard endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        assertTrue(game.canMoveHere(computer, endLimit, CardLocation.COMPUTER_SPEED_PILE));
    }

    @Test
    public void testStreetRaceGame_CanMoveHereComputerSpeedCardSpeedLimit() {
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        assertTrue(game.canMoveHere(computer, speedLimit,CardLocation.PLAYER_SPEED_PILE));
    }

    @Test
    public void testStreetRaceGame_CanMoveHereComputerSpeedCardSpeedLimitComputerPile() {
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        assertFalse(game.canMoveHere(computer, speedLimit,CardLocation.COMPUTER_SPEED_PILE));
    }

    @Test
    public void testStreetRaceGame_CanMoveHereComputerRemedyCard() {
        RemedyCard repair = new RemedyCard(RemedyCardType.REPAIR);
        assertTrue(game.canMoveHere(computer, repair, CardLocation.COMPUTER_BATTLE_PILE));
    }

    @Test
    public void testStreetRaceGame_CanMoveHereComputerHazardCard() {
        HazardCard accident = new HazardCard(HazardCardType.ACCIDENT);
        assertTrue(game.canMoveHere(computer, accident, CardLocation.PLAYER_BATTLE_PILE));
    }

    @Test
    public void testStreetRaceGame_CanMoveHereComputerHazardCardPlayerBattlePile() {
        HazardCard accident = new HazardCard(HazardCardType.ACCIDENT);
        assertFalse(game.canMoveHere(computer, accident, CardLocation.COMPUTER_BATTLE_PILE));
    }

    @Test
    public void testStreetRaceGame_CanMoveHereComputerSafetyCard() {
        SafetyCard punctureProof = new SafetyCard(SafetyCardType.PUNCTURE_PROOF);
        assertTrue(game.canMoveHere(computer, punctureProof, CardLocation.COMPUTER_SAFETY_PILE));
    }

    @Test
    public void testStreetRaceGame_CanMoveHereDrawPile() {
        SafetyCard punctureProof = new SafetyCard(SafetyCardType.PUNCTURE_PROOF);
        assertFalse(game.canMoveHere(computer, punctureProof, CardLocation.DRAW_PILE));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingMiles25() {
        DistanceCard miles25 = new DistanceCard(DistanceCardType.MILES_25);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        playerTableau.addToBattlePile(roll);
        assertTrue(game.isValidMove(player, miles25));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingMiles50() {
        DistanceCard miles50 = new DistanceCard(DistanceCardType.MILES_50);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        playerTableau.addToBattlePile(roll);
        assertTrue(game.isValidMove(player,miles50));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingMiles75() {
        DistanceCard miles75 = new DistanceCard(DistanceCardType.MILES_75);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getTableau().addToBattlePile(roll);
        assertTrue(game.isValidMove(player,miles75));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingMiles100() {
        DistanceCard miles100 = new DistanceCard(DistanceCardType.MILES_100);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getTableau().addToBattlePile(roll);
        assertTrue(game.isValidMove(player,miles100));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingMiles200() {
        DistanceCard miles200 = new DistanceCard(DistanceCardType.MILES_200);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getTableau().addToBattlePile(roll);
        assertTrue(game.isValidMove(player,miles200));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingTwoMiles200() {
        DistanceCard miles200 = new DistanceCard(DistanceCardType.MILES_200);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getTableau().addToBattlePile(roll);
        player.getTableau().addPlayed200s();
        player.getTableau().addPlayed200s();
        assertFalse(game.isValidMove(player, miles200));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingLimitMiles25() {
        DistanceCard miles25 = new DistanceCard(DistanceCardType.MILES_25);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        player.getTableau().addToBattlePile(roll);
        player.getTableau().addToSpeedPile(speedLimit);
        assertTrue(game.isValidMove(player, miles25));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingLimitMiles50() {
        DistanceCard miles50 = new DistanceCard(DistanceCardType.MILES_50);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        player.getTableau().addToBattlePile(roll);
        player.getTableau().addToSpeedPile(speedLimit);
        assertTrue(game.isValidMove(player, miles50));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingLimitMiles75() {
        DistanceCard miles75 = new DistanceCard(DistanceCardType.MILES_75);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        player.getTableau().addToBattlePile(roll);
        player.getTableau().addToSpeedPile(speedLimit);
        assertFalse(game.isValidMove(player, miles75));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingLimitMiles100() {
        DistanceCard miles100 = new DistanceCard(DistanceCardType.MILES_100);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        player.getTableau().addToBattlePile(roll);
        player.getTableau().addToSpeedPile(speedLimit);
        assertFalse(game.isValidMove(player, miles100));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingLimitMiles200() {
        DistanceCard miles200 = new DistanceCard(DistanceCardType.MILES_200);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        player.getTableau().addToBattlePile(roll);
        player.getTableau().addToSpeedPile(speedLimit);
        assertFalse(game.isValidMove(player, miles200));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingExceededMiles50() {
        DistanceCard miles50 = new DistanceCard(DistanceCardType.MILES_50);
        DistanceCard miles200 = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard miles200b = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard miles100 = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100b = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100c = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100d = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100e = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles75 = new DistanceCard(DistanceCardType.MILES_75);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getTableau().addToBattlePile(roll);
        player.getTableau().addToDistancePile(miles200);
        player.getTableau().addToDistancePile(miles200b);
        player.getTableau().addToDistancePile(miles100);
        player.getTableau().addToDistancePile(miles100b);
        player.getTableau().addToDistancePile(miles100c);
        player.getTableau().addToDistancePile(miles100d);
        player.getTableau().addToDistancePile(miles100e);
        player.getTableau().addToDistancePile(miles75);
        // totalMiles = 975 + 50
        player.getTableau().calculateTotalMiles();
        assertFalse(game.isValidMove(player, miles50));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingExceededMiles75() {
        DistanceCard miles75 = new DistanceCard(DistanceCardType.MILES_75);
        DistanceCard miles200 = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard miles200b = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard miles100 = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100b = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100c = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100d = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100e = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles75b = new DistanceCard(DistanceCardType.MILES_75);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getTableau().addToBattlePile(roll);
        player.getTableau().addToDistancePile(miles200);
        player.getTableau().addToDistancePile(miles200b);
        player.getTableau().addToDistancePile(miles100);
        player.getTableau().addToDistancePile(miles100b);
        player.getTableau().addToDistancePile(miles100c);
        player.getTableau().addToDistancePile(miles100d);
        player.getTableau().addToDistancePile(miles100e);
        player.getTableau().addToDistancePile(miles75);
        // totalMiles = 975 + 75
        player.getTableau().calculateTotalMiles();
        assertFalse(game.isValidMove(player, miles75b));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingExceededMiles100() {
        DistanceCard miles50 = new DistanceCard(DistanceCardType.MILES_50);
        DistanceCard miles200 = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard miles200b = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard miles100 = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100b = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100c = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100d = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100e = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100f = new DistanceCard(DistanceCardType.MILES_100);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getTableau().addToBattlePile(roll);
        player.getTableau().addToDistancePile(miles200);
        player.getTableau().addToDistancePile(miles200b);
        player.getTableau().addToDistancePile(miles100);
        player.getTableau().addToDistancePile(miles100b);
        player.getTableau().addToDistancePile(miles100c);
        player.getTableau().addToDistancePile(miles100d);
        player.getTableau().addToDistancePile(miles100e);
        player.getTableau().addToDistancePile(miles50);
        // totalMiles = 950 + 100
        player.getTableau().calculateTotalMiles();
        assertFalse(game.isValidMove(player, miles100f));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardRollingExceededMiles200() {
        DistanceCard miles50 = new DistanceCard(DistanceCardType.MILES_50);
        DistanceCard miles200 = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard miles200b = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard miles200c = new DistanceCard(DistanceCardType.MILES_200);
        DistanceCard miles100 = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100b = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100c = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100d = new DistanceCard(DistanceCardType.MILES_100);
        DistanceCard miles100e = new DistanceCard(DistanceCardType.MILES_100);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getTableau().addToBattlePile(roll);
        player.getTableau().addToDistancePile(miles200);
        player.getTableau().addToDistancePile(miles200b);
        player.getTableau().addToDistancePile(miles100);
        player.getTableau().addToDistancePile(miles100b);
        player.getTableau().addToDistancePile(miles100c);
        player.getTableau().addToDistancePile(miles100d);
        player.getTableau().addToDistancePile(miles100e);
        player.getTableau().addToDistancePile(miles50);
        // totalMiles = 950 + 200
        player.getTableau().calculateTotalMiles();
        assertFalse(game.isValidMove(player, miles200c));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveDistanceCardNotRolling() {
        DistanceCard miles75 = new DistanceCard(DistanceCardType.MILES_75);
        assertFalse(game.isValidMove(player, miles75));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveSpeedCardNoSpeedLimitEndLimit() {
        SpeedCard endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        assertFalse(game.isValidMove(player, endLimit));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveSpeedCardSpeedLimitEndLimit() {
        SpeedCard endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        playerTableau.addToSpeedPile(speedLimit);
        assertTrue(game.isValidMove(player, endLimit));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveSpeedCardNoLimitEndLimit() {
        SpeedCard endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        assertFalse(game.isValidMove(player, endLimit));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveSpeedCardOppSpeedNotEmptyOppBattleNotEmptyOppSpeedTopNotSpeedLimit() {
        SpeedCard endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        player.getOpponentTableau().addToSpeedPile(endLimit);
        HazardCard accident = new HazardCard(HazardCardType.ACCIDENT);
        player.getOpponentTableau().addToBattlePile(accident);
        assertTrue(game.isValidMove(player, speedLimit));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveSpeedCardOppSpeedNotEmptyOppBattleNotEmptyOppSpeedTopIsSpeedLimit() {
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        SpeedCard cardToTest = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        player.getOpponentTableau().addToSpeedPile(speedLimit);
        HazardCard accident = new HazardCard(HazardCardType.ACCIDENT);
        player.getOpponentTableau().addToBattlePile(accident);
        assertFalse(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveSpeedCardOppSpeedNotEmptyOppBattleEmptySpeedTopNotSpeedLimit() {
        SpeedCard endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        SpeedCard cardToTest = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        player.getOpponentTableau().addToSpeedPile(endLimit);
        assertTrue(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveSpeedCardOppSpeedNotEmptyOppBattleEmptySpeedTopIsSpeedLimit() {
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        SpeedCard cardToTest = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        player.getOpponentTableau().addToSpeedPile(speedLimit);
        assertFalse(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveSpeedCardOppSpeedEmpty() {
        SpeedCard cardToTest = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        assertTrue(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveSpeedCardHasRightOfWay() {
        SafetyCard rightOfWay = new SafetyCard(SafetyCardType.RIGHT_OF_WAY);
        SpeedCard cardToTest = new SpeedCard(SpeedCardType.SPEED_LIMIT);
        player.getOpponentTableau().addToSafetyPile(rightOfWay);
        assertFalse(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardHazardRollingHasDrivingAceAccidentCard() {
        HazardCard cardToTest = new HazardCard(HazardCardType.ACCIDENT);
        SafetyCard drivingAce = new SafetyCard(SafetyCardType.DRIVING_ACE);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getOpponentTableau().addToBattlePile(roll);
        player.getOpponentTableau().addToSafetyPile(drivingAce);
        assertFalse(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardHazardRollingAccidentCard() {
        HazardCard cardToTest = new HazardCard(HazardCardType.ACCIDENT);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getOpponentTableau().addToBattlePile(roll);
        assertTrue(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardHazardRollingPunctureProofFlatTireCard() {
        HazardCard cardToTest = new HazardCard(HazardCardType.FLAT_TIRE);
        SafetyCard punctureProof = new SafetyCard(SafetyCardType.PUNCTURE_PROOF);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getOpponentTableau().addToBattlePile(roll);
        player.getOpponentTableau().addToSafetyPile(punctureProof);
        assertFalse(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardHazardRollingFlatTireCard() {
        HazardCard cardToTest = new HazardCard(HazardCardType.FLAT_TIRE);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getOpponentTableau().addToBattlePile(roll);
        assertTrue(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardHazardRollingHasExtraTankOutOfGasCard() {
        HazardCard cardToTest = new HazardCard(HazardCardType.OUT_OF_GAS);
        SafetyCard extraTank = new SafetyCard(SafetyCardType.EXTRA_TANK);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getOpponentTableau().addToBattlePile(roll);
        player.getOpponentTableau().addToSafetyPile(extraTank);
        assertFalse(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardHazardRollingOutOfGasCard() {
        HazardCard cardToTest = new HazardCard(HazardCardType.OUT_OF_GAS);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getOpponentTableau().addToBattlePile(roll);
        assertTrue(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardHazardRollingHasRightOfWayStopCard() {
        HazardCard cardToTest = new HazardCard(HazardCardType.STOP);
        SafetyCard rightOfWay = new SafetyCard(SafetyCardType.RIGHT_OF_WAY);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getOpponentTableau().addToBattlePile(roll);
        player.getOpponentTableau().addToSafetyPile(rightOfWay);
        assertFalse(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardHazardRollingStopCard() {
        HazardCard cardToTest = new HazardCard(HazardCardType.STOP);
        RemedyCard roll = new RemedyCard(RemedyCardType.ROLL);
        player.getOpponentTableau().addToBattlePile(roll);
        assertTrue(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardHazardNotRolling() {
        HazardCard cardToTest = new HazardCard(HazardCardType.STOP);
        assertFalse(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardRemedyBattlePileNotEmptyHasOutOfGasGasolineCard() {
        RemedyCard cardToTest = new RemedyCard(RemedyCardType.GASOLINE);
        HazardCard outOfGas = new HazardCard(HazardCardType.OUT_OF_GAS);
        player.getTableau().addToBattlePile(outOfGas);
        assertTrue(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardRemedyBattlePileNotEmptyGasolineCard() {
        RemedyCard cardToTest = new RemedyCard(RemedyCardType.GASOLINE);
        HazardCard flatTire = new HazardCard(HazardCardType.FLAT_TIRE);
        player.getTableau().addToBattlePile(flatTire);
        assertFalse(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardRemedyBattlePileNotEmptyHasAccidentRepairCard() {
        RemedyCard cardToTest = new RemedyCard(RemedyCardType.REPAIR);
        HazardCard accident = new HazardCard(HazardCardType.ACCIDENT);
        player.getTableau().addToBattlePile(accident);
        assertTrue(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardRemedyBattlePileNotEmptyRepairCard() {
        RemedyCard cardToTest = new RemedyCard(RemedyCardType.REPAIR);
        HazardCard flatTire = new HazardCard(HazardCardType.FLAT_TIRE);
        player.getTableau().addToBattlePile(flatTire);
        assertFalse(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardRemedyBattlePileNotEmptyHasFlatTireSpareTireCard() {
        RemedyCard cardToTest = new RemedyCard(RemedyCardType.SPARE_TIRE);
        HazardCard flatTire = new HazardCard(HazardCardType.FLAT_TIRE);
        player.getTableau().addToBattlePile(flatTire);
        assertTrue(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardRemedyBattlePileNotEmptySpareTireCard() {
        RemedyCard cardToTest = new RemedyCard(RemedyCardType.SPARE_TIRE);
        HazardCard stop = new HazardCard(HazardCardType.STOP);
        player.getTableau().addToBattlePile(stop);
        assertFalse(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardRemedyBattlePileNotEmptyHasGasolineRollCard() {
        RemedyCard cardToTest = new RemedyCard(RemedyCardType.ROLL);
        RemedyCard gasoline = new RemedyCard(RemedyCardType.GASOLINE);
        player.getTableau().addToBattlePile(gasoline);
        assertTrue(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardRemedyBattlePileNotEmptyHasRepairRollCard() {
        RemedyCard cardToTest = new RemedyCard(RemedyCardType.ROLL);
        RemedyCard remedyCard = new RemedyCard(RemedyCardType.REPAIR);
        player.getTableau().addToBattlePile(remedyCard);
        assertTrue(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardRemedyBattlePileNotEmptyHasSpareTireRollCard() {
        RemedyCard cardToTest = new RemedyCard(RemedyCardType.ROLL);
        RemedyCard remedyCard = new RemedyCard(RemedyCardType.SPARE_TIRE);
        player.getTableau().addToBattlePile(remedyCard);
        assertTrue(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardRemedyBattlePileNotEmptyHasRollRollCard() {
        RemedyCard cardToTest = new RemedyCard(RemedyCardType.ROLL);
        RemedyCard remedyCard = new RemedyCard(RemedyCardType.ROLL);
        player.getTableau().addToBattlePile(remedyCard);
        assertFalse(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardRemedyBattlePileNotEmptyHasHazardCardRollCard() {
        RemedyCard cardToTest = new RemedyCard(RemedyCardType.ROLL);
        HazardCard hazardCard = new HazardCard(HazardCardType.FLAT_TIRE);
        player.getTableau().addToBattlePile(hazardCard);
        assertFalse(game.isValidMove(player, cardToTest));
    }

    @Test
    public void testStreetRaceGame_IsValidMoveBattleCardRemedyBattlePileEmptyRemedyCard() {
        RemedyCard cardToTest = new RemedyCard(RemedyCardType.ROLL);
        assertFalse(game.isValidMove(player, cardToTest));
    }

}