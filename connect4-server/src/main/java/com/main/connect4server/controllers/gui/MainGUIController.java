package com.main.connect4server.controllers.gui;

import com.main.connect4server.controllers.fxml.MainController;
import com.main.connect4server.server.Server;
import com.main.connect4server.settings.Constants;

public class MainGUIController {
    MainController mainController;

    public MainGUIController(MainController mainController) {
        this.mainController = mainController;
        this.mainController.startServerBtn.setOnAction(event -> startServer());
    }

    private void startServer() {
        Server server = new Server();

        new Thread(server).start();

        this.mainController.statusLabel.setText("Server is listening on port " + Constants.PORT + "...");
        this.mainController.startServerBtn.setDisable(true);
    }
}
