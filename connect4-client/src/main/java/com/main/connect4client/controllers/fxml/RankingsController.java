package com.main.connect4client.controllers.fxml;

import com.main.connect4client.controllers.gui.RankingsGUIController;
import com.main.connect4client.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Date;
import java.util.List;

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

    // private DataService dataService;

    private static List<Player> getPlayers() {
        return List.of(
                new Player(1L, "player1", 51, 5, new Date()),
                new Player(2L, "player2", 42, 8, new Date()),
                new Player(3L, "player3", 34, 6, new Date()),
                new Player(4L, "player4", 25, 10, new Date()),
                new Player(5L, "player5", 18, 12, new Date())
        );
    }

    @FXML
    public void initialize() {
        this.rankingsGUIController = new RankingsGUIController(this);

//        dataService = new DataService();
//
//        dataService.setOnSucceeded(event -> {
//            // remove "loading" label
//            int loadingLabelIndex = 1;
//            rankingsTable.getChildren().remove(loadingLabelIndex);
//
//            // Set vertical scrolling policy
//            rankingsScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//            // get players
//            List<Player> players = dataService.getValue();
//
//            populateTableRows(players);
//        });
//
//        dataService.setOnFailed(event -> {
//            Throwable exception = dataService.getException();
//            System.out.println(exception.getMessage());
//
//            loadingLabel.setText("Server error.");
//            loadingLabel.setTextFill(Color.rgb(220, 53, 69));
//        });
//
//        // Start the data loading service
//        dataService.start();
    }

//    private void populateTableRows(List<Player> players) {
//        int rank = 1;
//
//        for (Player player : players) {
//            try {
//                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("table-row.fxml"));
//
//                HBox tableRow = fxmlLoader.load();
//
//                TableRowController tableRowController = fxmlLoader.getController();
//
//                tableRowController.setRank(rank);
//                tableRowController.setUsername(player.getUsername());
//                tableRowController.setWins(player.getWins());
//                tableRowController.setDefeats(player.getDefeats());
//                tableRowController.setMatchesPlayed(player.getWins() + player.getDefeats());
//                tableRowController.setRegisteredAt(new Date());
//
//                rankingsData.getChildren().add(tableRow);
//
//                rank++;
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//    public void backToMain() throws IOException {
//        Stage stage = (Stage) rankingContainer.getScene().getWindow();
//
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
//
//        Scene scene = new Scene(fxmlLoader.load());
//
//        stage.setScene(scene);
//        stage.setTitle("Connect4 - Main");
//    }
//
//    private static class DataService extends Service<List<Player>> {
//        @Override
//        protected Task<List<Player>> createTask() {
//            return new Task<>() {
//                @Override
//                protected List<Player> call() throws Exception {
//                    // Simulate data loading delay (replace this with your actual data loading logic)
//                    Thread.sleep(2000);
//
//                    // Simulate an error during data loading
//                    // throw new RuntimeException();
//
//                    // Simulate fetching data
//                    return getPlayers();
//                }
//            };
//        }
//    }
}
