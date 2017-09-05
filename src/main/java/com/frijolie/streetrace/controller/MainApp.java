package com.frijolie.streetrace.controller;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainApp extends Application {

    private static StreetRace game = new StreetRace();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ParentWindow.fxml"));

        Scene scene = new Scene(root);

        scene.getStylesheets().add("/styles/parentwindow.css");
        scene.getStylesheets().add("/styles/mainwindow.css");
        stage.setTitle("StreetRace");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/images/checkered-flag.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
        game.startGame();
    }

}
