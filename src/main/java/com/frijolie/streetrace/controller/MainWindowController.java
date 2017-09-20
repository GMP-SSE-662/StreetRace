package com.frijolie.streetrace.controller;

import com.frijolie.streetrace.model.StreetRaceGame;
import com.frijolie.streetrace.model.cards.Card;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

public class MainWindowController implements Initializable {

    private StreetRaceGame game = StreetRaceGame.getInstance();
    private ObservableList<Card> playerHand = game.getPlayerHand();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}