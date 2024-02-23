package com.main.connect4server.controllers.fxml;

import com.main.connect4server.controllers.gui.MainGUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
    private MainGUIController mainGUIController;

    @FXML
    public Button startServerBtn;

    @FXML
    public Label statusLabel;

    @FXML
    public void initialize() throws IllegalArgumentException {
        this.mainGUIController = new MainGUIController(this);
    }
}