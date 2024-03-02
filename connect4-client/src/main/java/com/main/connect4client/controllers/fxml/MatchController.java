package com.main.connect4client.controllers.fxml;

import com.main.connect4client.controllers.gui.MatchGUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class MatchController {
    public MatchGUIController matchGUIController;

    @FXML
    public GridPane rootGridPane;

    @FXML
    public Pane gamePane;

    @FXML
    public Label statusLabel;

    @FXML
    public Circle statusCircle;

    @FXML
    public void initialize() {
        this.matchGUIController = new MatchGUIController(this);
    }
}
