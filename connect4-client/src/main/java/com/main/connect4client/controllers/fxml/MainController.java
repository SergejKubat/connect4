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
    public Label welcomeLabel;

    @FXML
    public Label emailLabel;

    @FXML
    public Label winsLabel;

    @FXML
    public Label defeatsLabel;

    @FXML
    public Label matchesPlayedLabel;

    @FXML
    public Label registeredAtLabel;

    @FXML
    public Button signOutBtn;

    public void initialize() throws IllegalArgumentException {
        this.mainGUIController = new MainGUIController(this);
    }
}
