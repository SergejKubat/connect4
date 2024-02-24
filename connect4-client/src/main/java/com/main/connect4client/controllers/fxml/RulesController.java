package com.main.connect4client.controllers.fxml;

import com.main.connect4client.controllers.gui.RulesGUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class RulesController {
    public RulesGUIController rulesGUIController;

    @FXML
    public AnchorPane rulesContainer;

    @FXML
    public Button backToMainBtn;

    public void initialize() throws IllegalArgumentException {
        this.rulesGUIController = new RulesGUIController(this);
    }
}
