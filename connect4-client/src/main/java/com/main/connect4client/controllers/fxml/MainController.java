package com.main.connect4client.controllers.fxml;

import com.main.connect4client.controllers.gui.MainGUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class MainController {
    public MainGUIController mainGUIController;

    @FXML
    public AnchorPane mainContainer;

    @FXML
    public MenuItem newMatchItem;

    @FXML
    public MenuItem rankingsItem;

    @FXML
    public MenuItem rulesItem;

    @FXML
    public MenuItem tutorialItem;

    @FXML
    public MenuItem aboutItem;

    @FXML
    public Label username;

    @FXML
    public Label email;

    @FXML
    public Label wins;

    @FXML
    public Label registeredAt;

    @FXML
    public Button signOutBtn;

    public void initialize() throws IllegalArgumentException {
        this.mainGUIController = new MainGUIController(this);
    }
}
