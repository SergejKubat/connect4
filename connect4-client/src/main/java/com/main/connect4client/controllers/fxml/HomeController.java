package com.main.connect4client.controllers.fxml;

import com.main.connect4client.controllers.gui.HomeGUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class HomeController {
    public HomeGUIController homeGUIController;

    @FXML
    public AnchorPane homeContainer;

    @FXML
    public Button openSignInBtn;

    @FXML
    public Button openSignUpBtn;

    public void initialize() throws IllegalArgumentException {
        this.homeGUIController = new HomeGUIController(this);
    }
}