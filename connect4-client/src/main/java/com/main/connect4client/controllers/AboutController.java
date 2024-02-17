package com.main.connect4client.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AboutController {
    @FXML
    private AnchorPane aboutContainer;

    public void exitAbout() {
        Stage stage = (Stage) aboutContainer.getScene().getWindow();
        stage.close();
    }
}
