package com.main.connect4client.controllers.fxml;

import com.main.connect4client.controllers.gui.AboutGUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AboutController {
    public AboutGUIController aboutGUIController;

    @FXML
    public AnchorPane aboutContainer;

    @FXML
    public Button exitBtn;

    @FXML
    public void initialize() throws IllegalArgumentException {
        this.aboutGUIController = new AboutGUIController(this);
    }

    public void exitAbout() {
        Stage stage = (Stage) aboutContainer.getScene().getWindow();
        stage.close();
    }
}
