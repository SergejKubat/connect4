package com.main.connect4client.controllers.gui;

import com.main.connect4client.Main;
import com.main.connect4client.controllers.fxml.RankingsController;
import com.main.connect4client.controllers.fxml.TableRowController;
import com.main.connect4client.models.Player;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class RankingsGUIController {
    private final RankingsController rankingsController;

    private DataService dataService;

    public RankingsGUIController(RankingsController rankingsController) {
        this.rankingsController = rankingsController;
        this.rankingsController.backToMainBtn.setOnAction(event -> backToMain());

        startService();
    }

    private static List<Player> getPlayers() {
        return List.of(
                new Player(1L, "player1", 51, 5, new Date()),
                new Player(2L, "player2", 42, 8, new Date()),
                new Player(3L, "player3", 34, 6, new Date()),
                new Player(4L, "player4", 25, 10, new Date()),
                new Player(5L, "player5", 18, 12, new Date())
        );
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
            Throwable exception = dataService.getException();
            System.out.println(exception.getMessage());

            this.rankingsController.loadingLabel.setText("Server error.");
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
                tableRowController.setDefeats(player.getDefeats());
                tableRowController.setMatchesPlayed(player.getWins() + player.getDefeats());
                tableRowController.setRegisteredAt(new Date());

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
                protected List<Player> call() throws Exception {
                    // Simulate data loading delay (replace this with your actual data loading logic)
                    Thread.sleep(2000);

                    // Simulate an error during data loading
                    // throw new RuntimeException();

                    // Simulate fetching data
                    return getPlayers();
                }
            };
        }
    }
}
