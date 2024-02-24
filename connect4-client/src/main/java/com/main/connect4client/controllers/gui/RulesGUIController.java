package com.main.connect4client.controllers.gui;

import com.main.connect4client.Main;
import com.main.connect4client.controllers.fxml.RulesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RulesGUIController {
    private final RulesController rulesController;

    public RulesGUIController(RulesController rulesController) {
        this.rulesController = rulesController;
        this.rulesController.backToMainBtn.setOnAction(event -> backToMain());
    }

    public void backToMain() {
        Stage stage = (Stage) this.rulesController.rulesContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setTitle("Connect4 - Main");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
