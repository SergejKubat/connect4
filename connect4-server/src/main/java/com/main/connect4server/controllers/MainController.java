package com.main.connect4server.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Button startServerBtn;

    @FXML
    private Label statusLabel;

    public void startServer() {
        startServerBtn.setDisable(true);
        statusLabel.setText("Server is listening on port 5000...");
    }
}