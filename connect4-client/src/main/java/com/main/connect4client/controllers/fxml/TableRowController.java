package com.main.connect4client.controllers.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TableRowController {
    @FXML
    private Label rankLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label winsLabel;

    @FXML
    private Label registeredAtLabel;

    public void setRank(int rank) {
        rankLabel.setText(rank + ".");
    }

    public void setUsername(String username) {
        usernameLabel.setText(username);
    }

    public void setWins(int wins) {
        winsLabel.setText(String.valueOf(wins));
    }

    public void setRegisteredAt(Date registeredAt) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        registeredAtLabel.setText(dateFormat.format(registeredAt));
    }
}
