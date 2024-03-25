package com.main.connect4client.controllers.gui;

import com.main.connect4client.Main;
import com.main.connect4client.controllers.client.ClientController;
import com.main.connect4client.controllers.fxml.RankingsController;
import com.main.connect4client.controllers.fxml.TableRowController;
import com.main.connect4shared.domain.Player;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class RankingsGUIController {
    private final RankingsController rankingsController;

    private DataService dataService;

    public RankingsGUIController(RankingsController rankingsController) {
        this.rankingsController = rankingsController;
        this.rankingsController.backToMainBtn.setOnAction(event -> backToMain());

        startService();
    }

    private void startService() {
        dataService = new DataService();

        dataService.setOnSucceeded(event -> {
            // remove "loading" label
            int loadingLabelIndex = 1;
            this.rankingsController.rankingsTable.getChildren().remove(loadingLabelIndex);

            // Set vertical scrolling policy
            this.rankingsController.rankingsScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

            // get players
            List<Player> players = dataService.getValue();

            populateTableRows(players);
        });

        dataService.setOnFailed(event -> {
            this.rankingsController.loadingLabel.setText("Error loading ranking list.");
            this.rankingsController.loadingLabel.setTextFill(Color.rgb(220, 53, 69));
        });

        // Start the data loading service
        dataService.start();
    }

    private void populateTableRows(List<Player> players) {
        int rank = 1;

        for (Player player : players) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("table-row.fxml"));

                HBox tableRow = fxmlLoader.load();

                TableRowController tableRowController = fxmlLoader.getController();

                tableRowController.setRank(rank);
                tableRowController.setUsername(player.getUsername());
                tableRowController.setWins(player.getWins());
                tableRowController.setRegisteredAt(player.getRegisteredAt());

                this.rankingsController.rankingsData.getChildren().add(tableRow);

                rank++;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void backToMain() {
        Stage stage = (Stage) this.rankingsController.rankingContainer.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setTitle("Connect4 - Main");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static class DataService extends Service<List<Player>> {
        @Override
        protected Task<List<Player>> createTask() {
            return new Task<>() {
                @Override
                protected List<Player> call() {
                    try {
                        return ClientController.getInstance().getAllPlayers();
                    } catch (Exception ex) {
                        throw new RuntimeException();
                    }
                }
            };
        }
    }
}
