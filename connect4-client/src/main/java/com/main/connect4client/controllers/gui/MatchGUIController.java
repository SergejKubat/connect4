package com.main.connect4client.controllers.gui;

import com.main.connect4client.controllers.client.ClientController;
import com.main.connect4client.controllers.fxml.MatchController;
import com.main.connect4shared.domain.GameMove;
import com.main.connect4shared.response.Response;
import com.main.connect4shared.response.ResponseStatus;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchGUIController {
    private static final double PLACEHOLDER_SIZE = 80;

    private static final int COLUMNS = 7;

    private static final int ROWS = 6;

    private boolean continueToPlay = true;

    private boolean playerOneMove = false;

    private boolean playerOneTurn = false;

    private boolean waiting = true;

    private int rowSelected;

    private int columnSelected;

    private final Token[][] board = new Token[COLUMNS][ROWS];

    private final MatchController matchController;

    public MatchGUIController(MatchController matchController) {
        this.matchController = matchController;

        Shape board = generateBoard();
        List<Rectangle> columns = generateColumns();

        this.matchController.rootGridPane.add(board, 0, 0);
        this.matchController.rootGridPane.getChildren().addAll(columns);
    }

    private Shape generateBoard() {
        Shape shape = new Rectangle((COLUMNS + 1) * PLACEHOLDER_SIZE, (ROWS + 1) * PLACEHOLDER_SIZE);

        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                Circle circle = new Circle(PLACEHOLDER_SIZE / 2);
                circle.setCenterX(PLACEHOLDER_SIZE / 2);
                circle.setCenterY(PLACEHOLDER_SIZE / 2);
                circle.setTranslateX(x * (PLACEHOLDER_SIZE + 5) + PLACEHOLDER_SIZE / 4);
                circle.setTranslateY(y * (PLACEHOLDER_SIZE + 5) + PLACEHOLDER_SIZE / 4);

                shape = Shape.subtract(shape, circle);
            }
        }

        Light.Distant light = new Light.Distant();

        light.setAzimuth(45.0);
        light.setElevation(30.0);

        Lighting lighting = new Lighting();

        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        shape.setFill(Color.rgb(14, 28, 41));
        shape.setEffect(lighting);

        return shape;
    }

    private List<Rectangle> generateColumns() {
        connectToServer();

        List<Rectangle> rectangles = new ArrayList<>();

        for (int x = 0; x < COLUMNS; x++) {
            Rectangle rectangle = new Rectangle(PLACEHOLDER_SIZE, (ROWS + 1) * PLACEHOLDER_SIZE);

            rectangle.setTranslateX(x * (PLACEHOLDER_SIZE + 5) + PLACEHOLDER_SIZE / 4);
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setOnMouseEntered(event -> {
                rectangle.setCursor(Cursor.HAND);
                rectangle.setFill(Color.rgb(122, 136, 171, 0.2));
            });
            rectangle.setOnMouseExited(e -> rectangle.setFill(Color.TRANSPARENT));

            int column = x;

            rectangle.setOnMouseClicked(event -> {
                try {
                    int row = ClientController.getInstance().getAvailableRow(column);

                    if (row != -1) {
                        if (playerOneTurn) {
                            placeToken(new Token(playerOneMove), column, row);

                            playerOneTurn = false;
                            rowSelected = row;
                            columnSelected = column;
                            waiting = false;
                            System.out.println("Waiting for server...");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            rectangles.add(rectangle);
        }

        return rectangles;
    }

    private void placeToken(Token token, int column, int row) {
        board[column][row] = token;

        this.matchController.gamePane.getChildren().add(token);

        token.setTranslateX(column * (PLACEHOLDER_SIZE + 5) + PLACEHOLDER_SIZE / 4);

        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), token);

        animation.setToY(row * (PLACEHOLDER_SIZE + 5) + PLACEHOLDER_SIZE / 4);
        animation.play();
    }

    private void connectToServer() {
        new Thread(() -> {
            try {
                playerOneMove = true;
                playerOneTurn = true;
                // Platform.runLater(() -> lblStatus.setText("Your turn"));

                // Continue to play
                while (continueToPlay) {
                    waitForPlayerAction(); // Wait for player 1 to move
                    Response response = sendMove(); // Send the move to the server
                    receiveInfoFromServer(response);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    private void waitForPlayerAction() throws InterruptedException {
        while (waiting) {
            Thread.sleep(3000);
        }

        waiting = true;
    }

    private Response sendMove() {
        Response response = null;

        try {
            response = ClientController.getInstance().sendMove(rowSelected, columnSelected);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return response;
    }

    private void receiveInfoFromServer(Response response) {

        if (response.getStatus() == ResponseStatus.PLAYER_1_WON) {
            continueToPlay = false;

            System.out.println("You won!");
            // Platform.runLater(() -> showAlert("Congratulations", "You won! Player wins updated!"));
            // Platform.runLater(() -> GameStage.getInstance().setScene("client/forms/main/FXMLMain.fxml"));
        } else if (response.getStatus() == ResponseStatus.PLAYER_2_WON) {
            continueToPlay = false;

            receiveMove((GameMove) response.getResult());

            System.out.println("Computer player has won!");
            // Platform.runLater(() -> showAlert("Game over", "Computer has won! Player wins updated!"));
            // Platform.runLater(() -> GameStage.getInstance().setScene("client/forms/main/FXMLMain.fxml"));
        } else if (response.getStatus() == ResponseStatus.DRAW) {
            continueToPlay = false;

            System.out.println("No winner!");
            // Platform.runLater(() -> showAlert("Game over", "No winner!"));
            // Platform.runLater(() -> GameStage.getInstance().setScene("client/forms/main/FXMLMain.fxml"));
        } else if (response.getStatus() == ResponseStatus.ERROR) {
            System.out.println("Player wins did not update!");
            // showAlert("Error", "Player wins did not update");
        } else {
            receiveMove((GameMove) response.getResult());
            System.out.println("Your turn!");
            // Platform.runLater(() -> lblStatus.setText("Your turn"));
            playerOneTurn = true;
        }
    }

    private void receiveMove(GameMove move) {
        try {
            int row = move.getRow();
            int column = move.getCol();

            System.out.println("Server move: " + row + " " + column);

            Platform.runLater(() -> placeToken(new Token(!playerOneMove), column, row));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static class Token extends Circle {

        public Token(boolean isRed) {
            super(PLACEHOLDER_SIZE / 2, isRed ? Color.RED : Color.YELLOW);

            setCenterX(PLACEHOLDER_SIZE / 2);
            setCenterY(PLACEHOLDER_SIZE / 2);
        }
    }
}
