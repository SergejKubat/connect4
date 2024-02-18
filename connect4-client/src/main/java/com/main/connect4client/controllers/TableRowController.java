package com.main.connect4client.controllers;

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
    private Label defeatsLabel;

    @FXML
    private Label matchesPlayedLabel;

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

    public void setDefeats(int defeats) {
        defeatsLabel.setText(String.valueOf(defeats));
    }

    public void setMatchesPlayed(int matchesPlayed) {
        matchesPlayedLabel.setText(String.valueOf(matchesPlayed));
    }

    public void setRegisteredAt(Date registeredAt) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        registeredAtLabel.setText(dateFormat.format(registeredAt));
    }
}
