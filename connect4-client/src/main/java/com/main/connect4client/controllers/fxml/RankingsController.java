package com.main.connect4client.controllers.fxml;

import com.main.connect4client.controllers.gui.RankingsGUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class RankingsController {
    public RankingsGUIController rankingsGUIController;

    @FXML
    public AnchorPane rankingContainer;

    @FXML
    public VBox rankingsTable;

    @FXML
    public VBox rankingsData;

    @FXML
    public Label loadingLabel;

    @FXML
    public ScrollPane rankingsScrollPane;

    @FXML
    public Button backToMainBtn;

    @FXML
    public void initialize() {
        this.rankingsGUIController = new RankingsGUIController(this);
    }
}
