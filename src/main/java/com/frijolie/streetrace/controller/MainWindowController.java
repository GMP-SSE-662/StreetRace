package com.frijolie.streetrace.controller;

import com.frijolie.streetrace.model.Hand;
import com.frijolie.streetrace.model.cards.Card;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MainWindowController implements Initializable {

    ObservableList<Card> observablePlayerHand;
    Hand playerHand;

    @FXML
    private AnchorPane computerPane;

    @FXML
    private AnchorPane playerPane;

    @FXML
    private HBox playerHandHBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
