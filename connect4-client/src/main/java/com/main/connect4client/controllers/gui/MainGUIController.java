package com.main.connect4client.controllers.gui;

import com.main.connect4client.Main;
import com.main.connect4client.controllers.fxml.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class MainGUIController {
    private final MainController mainController;

    public MainGUIController(MainController mainController) {
        this.mainController = mainController;
        this.mainController.newMatchItem.setOnAction(event -> openNewMatchPage());
        this.mainController.rankingsItem.setOnAction(event -> openRankingsPage());
        this.mainController.rulesItem.setOnAction(event -> openRulesPage());
        this.mainController.tutorialItem.setOnAction(event -> openTutorial());
        this.mainController.aboutItem.setOnAction(event -> openAboutPage());
        this.mainController.signOutBtn.setOnAction(event -> signOut());
    }

    public void openNewMatchPage() {
        Stage stage = (Stage) this.mainController.mainContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("match-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setTitle("Connect4 - Match");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void openRankingsPage() {
        Stage stage = (Stage) this.mainController.mainContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ranking-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setTitle("Connect4 - Rankings");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void openRulesPage() {
        Stage stage = (Stage) this.mainController.mainContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("rules-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setTitle("Connect4 - Rules");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void openTutorial() {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();

                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    desktop.browse(URI.create("https://www.youtube.com/watch?v=yDWPi1pZ0Po"));
                }
            }
        } catch (IOException | InternalError ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void openAboutPage() {
        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("about-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load(), 400, 300);

            stage.setScene(scene);
            stage.setTitle("About");
            stage.setResizable(false);

            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void signOut() {
        Stage stage = (Stage) this.mainController.mainContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setTitle("Connect4 - Home");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}