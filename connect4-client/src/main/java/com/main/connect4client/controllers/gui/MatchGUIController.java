package com.main.connect4client.controllers.gui;

import com.main.connect4client.Main;
import com.main.connect4client.controllers.client.ClientController;
import com.main.connect4client.controllers.fxml.MatchController;
import com.main.connect4client.utils.Message;
import com.main.connect4shared.domain.GameMove;
import com.main.connect4shared.response.Response;
import com.main.connect4shared.response.ResponseStatus;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatchGUIController {
    private static final double PLACEHOLDER_SIZE = 80;
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;
    private final MatchController matchController;
    private boolean continueToPlay = true;

    private boolean humanPlayerMove = false;

    private boolean humanPlayerTurn = false;

    private boolean waiting = true;

    private int rowSelected;

    private int columnSelected;

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
            int column = x;

            Rectangle rectangle = new Rectangle(PLACEHOLDER_SIZE, (ROWS + 1) * PLACEHOLDER_SIZE);

            rectangle.setTranslateX(x * (PLACEHOLDER_SIZE + 5) + PLACEHOLDER_SIZE / 4);
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setOnMouseEntered(event -> {
                rectangle.setCursor(Cursor.HAND);
                rectangle.setFill(Color.rgb(122, 136, 171, 0.2));
            });
            rectangle.setOnMouseExited(e -> rectangle.setFill(Color.TRANSPARENT));
            rectangle.setOnMouseClicked(event -> onBoardClick(column));

            rectangles.add(rectangle);
        }

        return rectangles;
    }

    private void onBoardClick(int column) {
        try {
            int row = ClientController.getInstance().getAvailableRow(column);

            if (row > -1) {
                if (humanPlayerTurn) {
                    placeToken(new Token(humanPlayerMove), column, row);

                    humanPlayerTurn = false;
                    waiting = false;
                    rowSelected = row;
                    columnSelected = column;

                    // update status
                    Platform.runLater(() -> {
                        this.matchController.statusLabel.setText("Computer's turn");
                        this.matchController.statusCircle.setFill(Color.YELLOW);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void placeToken(Token token, int column, int row) {
        this.matchController.gamePane.getChildren().add(token);

        token.setTranslateX(column * (PLACEHOLDER_SIZE + 5) + PLACEHOLDER_SIZE / 4);

        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), token);

        animation.setToY(row * (PLACEHOLDER_SIZE + 5) + PLACEHOLDER_SIZE / 4);
        animation.play();
    }

    private void connectToServer() {
        new Thread(() -> {
            try {
                humanPlayerMove = true;
                humanPlayerTurn = true;

                // update status
                Platform.runLater(() -> {
                    this.matchController.statusLabel.setText("Your turn");
                    this.matchController.statusCircle.setFill(Color.RED);
                });

                // Continue to play
                while (continueToPlay) {
                    waitForPlayerAction();

                    Response response = sendMove();

                    receiveMove(response);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    private void waitForPlayerAction() throws InterruptedException {
        while (waiting) {
            Thread.sleep(2500);
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

    private void receiveMove(Response response) {
        ResponseStatus status = response.getStatus();

        switch (status) {
            case PLAYER_1_WON -> {
                continueToPlay = false;

                Platform.runLater(() -> {
                    Message.showMessage("Congratulations, you won!", Alert.AlertType.INFORMATION);
                    openMainPage();
                });
            }
            case PLAYER_2_WON -> {
                continueToPlay = false;

                handleMove((GameMove) response.getResult());

                Platform.runLater(() -> {
                    Message.showMessage("Computer player has won!", Alert.AlertType.INFORMATION);
                    openMainPage();
                });
            }
            case DRAW -> {
                continueToPlay = false;

                Platform.runLater(() -> {
                    Message.showMessage("The match is draw!", Alert.AlertType.INFORMATION);
                    openMainPage();
                });
            }
            case ERROR -> Platform.runLater(() -> {
                Message.showMessage("Server error!", Alert.AlertType.ERROR);
                openMainPage();
            });
            default -> {
                handleMove((GameMove) response.getResult());

                humanPlayerTurn = true;

                // update status
                Platform.runLater(() -> {
                    this.matchController.statusLabel.setText("Your turn");
                    this.matchController.statusCircle.setFill(Color.RED);
                });
            }
        }
    }

    private void handleMove(GameMove move) {
        try {
            int row = move.getRow();
            int column = move.getCol();

            Platform.runLater(() -> placeToken(new Token(!humanPlayerMove), column, row));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void openMainPage() {
        Stage stage = (Stage) this.matchController.rootGridPane.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
            stage.setTitle("Connect4 - Main");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
