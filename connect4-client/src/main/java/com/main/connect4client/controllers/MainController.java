package com.main.connect4client.controllers;

import com.main.connect4client.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class MainController {
    @FXML
    private AnchorPane mainContainer;

    public void openRulesPage() throws IOException {
        Stage stage = (Stage) mainContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("rules-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Connect4 - Rules");
    }

    public void openTutorial() {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(URI.create("https://www.youtube.com/watch?v=yDWPi1pZ0Po"));
                }
            }
        } catch (IOException | InternalError e) {
            e.printStackTrace();
        }
    }

    public void openAboutPage() throws IOException {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("about-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 400, 300);

        stage.setScene(scene);
        stage.setTitle("About");
        stage.setResizable(false);

        stage.show();
    }

    public void openNewGamePage() throws IOException {
        Stage stage = (Stage) mainContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("match-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Connect4 - Match");
    }

    public void openRankingPage() throws IOException {
        Stage stage = (Stage) mainContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ranking-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Connect4 - Rankings");
    }

    public void signOut() throws IOException {
        Stage stage = (Stage) mainContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Connect4 - Home");
    }
}
