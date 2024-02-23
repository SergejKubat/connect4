package com.main.connect4server.controllers.gui;

import com.main.connect4server.controllers.fxml.MainController;
import com.main.connect4server.server.Server;

import java.io.IOException;

public class MainGUIController {
    MainController mainController;

    public MainGUIController(MainController mainController) {
        this.mainController = mainController;
        this.mainController.startServerBtn.setOnAction(event -> startServer());
    }

    private void startServer() {
        try {
            Server server = new Server(5000);

            server.start();

            this.mainController.statusLabel.setText("Server is listening on port 5000...");
            this.mainController.startServerBtn.setDisable(true);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
