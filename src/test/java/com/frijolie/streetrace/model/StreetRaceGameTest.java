package com.frijolie.streetrace.model;

import com.frijolie.streetrace.model.cards.BattleCard;
import com.frijolie.streetrace.model.cards.Card;
import com.frijolie.streetrace.model.cards.HazardCard;
import com.frijolie.streetrace.model.cards.HazardCardType;
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

/**
 *
 * @author LB8942
 */
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
//        System.out.println("\n----- Player Hand -----");
//        game.getPlayerHand().displayHand();
    }

    @Test
    public void testStreetRaceGame_ComputerHandContainsSevenCardsAfterDeal() {
        assertEquals(computerHand.size(), 7);
//        System.out.println("\n----- Computer Hand -----");
//        game.getComputerHand().displayHand();
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
    public void testStreetRaceGame_PlayerSpeedPileTopCardFalse() {
        playerTableau.getSpeedPile().clear();
        SpeedCard speedLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        SpeedCard endLimit = new SpeedCard(SpeedCardType.END_LIMIT);
        playerTableau.addToSpeedPile(speedLimit);
        playerTableau.addToSpeedPile(endLimit);
        assertFalse(game.speedPileTopCard(player) == speedLimit);
    }

    @Test
    public void testStreetRaceGame_PlayerBattlePileTopCardTrue() {
        playerTableau.getBattlePile().clear();
        BattleCard stop = new HazardCard(HazardCardType.STOP);
        playerTableau.addToBattlePile(stop);
        assertEquals(playerTableau.getBattlePileTopCard(),stop);
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
    public void testStreetRaceGame_CardLocationDiscardPile() {
        discardPile.clear();
        BattleCard stop = new HazardCard(HazardCardType.STOP);
        game.addToDiscardPile(stop);
        assertTrue(game.find(stop) == CardLocation.DISCARD_PILE);
    }

}