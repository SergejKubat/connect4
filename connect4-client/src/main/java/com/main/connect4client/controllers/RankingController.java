package com.main.connect4client.controllers;

import com.main.connect4client.Main;
import com.main.connect4client.models.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class RankingController {
    private final ObservableList<Player> rankingData = FXCollections.observableArrayList();

    @FXML
    private AnchorPane rankingContainer;

    @FXML
    private TableView<Player> rankingTable;

    @FXML
    private TableColumn<Player, Long> numberColumn;

    @FXML
    private TableColumn<Player, String> usernameColumn;

    @FXML
    private TableColumn<Player, Integer> winsColumn;

    @FXML
    private TableColumn<Player, Integer> defeatsColumn;

    @FXML
    private TableColumn<Player, Date> registeredAtColumn;

    @FXML
    public void initialize() {
        // Set up the columns
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        defeatsColumn.setCellValueFactory(new PropertyValueFactory<>("defeats"));
        registeredAtColumn.setCellValueFactory(new PropertyValueFactory<>("registeredAt"));

        // Add hardcoded data
        rankingData.add(new Player(1L, "Player1", 25, 5, new Date()));
        rankingData.add(new Player(2L, "Player2", 21, 6, new Date()));
        rankingData.add(new Player(3L, "Player3", 18, 8, new Date()));
        rankingData.add(new Player(4L, "Player4", 14, 8, new Date()));
        rankingData.add(new Player(5L, "Player5", 10, 5, new Date()));

        // Bind the data to the TableView
        rankingTable.setItems(rankingData);
    }

    public void backToMain() throws IOException {
        Stage stage = (Stage) rankingContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.setTitle("Connect4 - Main");
    }
}
