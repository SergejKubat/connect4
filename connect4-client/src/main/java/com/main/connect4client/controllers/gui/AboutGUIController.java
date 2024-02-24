package com.main.connect4client.controllers.gui;

import com.main.connect4client.controllers.fxml.AboutController;
import javafx.stage.Stage;

public class AboutGUIController {
    private final AboutController aboutController;

    public AboutGUIController(AboutController aboutController) {
        this.aboutController = aboutController;
        this.aboutController.exitBtn.setOnAction(event -> exitAbout());
    }

    public void exitAbout() {
        Stage stage = (Stage) this.aboutController.aboutContainer.getScene().getWindow();
        stage.close();
    }
}
